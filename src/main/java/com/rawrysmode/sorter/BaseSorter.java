package com.rawrysmode.sorter;

import com.rawrysmode.parser.parameters.Ordering;
import com.rawrysmode.parser.parameters.Parameters;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class BaseSorter<E extends Comparable<E>> {

    abstract E parseValue(String string);

    public void executeMerge(Parameters parameters) throws IOException {
        ArrayList<BufferedReader> bufferedReaders = parameters.getBufferedReaders();
        String outputFilename = parameters.getOutputFilename();
        Ordering ordering = parameters.getOrdering();

        E e;
        ArrayList<E> arrayList = new ArrayList<>();
        for (BufferedReader bufferedReader : bufferedReaders) {
            e = findValidValue(bufferedReader);
            if (e == null) bufferedReaders.remove(bufferedReader);
            arrayList.add(e);
        }

        int index;
        E temporary;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
        while (!bufferedReaders.isEmpty()) {
            index = getMinOrMaxByOrdering(arrayList, ordering);
            bufferedWriter.append((temporary = arrayList.remove(index)).toString());
            bufferedWriter.newLine();

            e = findValidValue(bufferedReaders.get(index));
            if(e != null && checkRightOrdered(temporary, e, ordering)) {
                arrayList.add(index, e);
            } else {
                bufferedReaders.remove(bufferedReaders.get(index));
            }
        }
        bufferedWriter.close();
    }

    private boolean checkRightOrdered(E e, E e1, Ordering ordering){
        if (ordering == Ordering.ASCENDING) {
            return e.compareTo(e1) < 1;
        }
        return e.compareTo(e1) > 0;
    }

    private int getMinOrMaxByOrdering(ArrayList<E> arrayList, Ordering ordering) {
        if (ordering == Ordering.ASCENDING) {
            return arrayList.indexOf(Collections.min(arrayList));
        }
        return arrayList.indexOf(Collections.max(arrayList));
    }

    private E findValidValue(BufferedReader bufferedReader) {
        String value;
        do {
            try {
                value = bufferedReader.readLine();
                if (value == null) return null;
                if (value.contains(" ") || value.isEmpty()) continue;
                return parseValue(value);
            } catch (NumberFormatException | IOException ignored) {
            }
        } while (true);
    }
}
