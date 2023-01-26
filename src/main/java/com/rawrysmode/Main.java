package com.rawrysmode;

import com.rawrysmode.parser.ArgumentsParser;
import com.rawrysmode.parser.parameters.Parameters;
import com.rawrysmode.parser.parameters.Type;
import com.rawrysmode.sorter.IntegerSorter;
import com.rawrysmode.sorter.BaseSorter;
import com.rawrysmode.sorter.StringSorter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Parameters parameters = ArgumentsParser.parse(args);
        if (parameters.getType() == Type.INTEGER) {
            BaseSorter<Integer> baseSorter = new IntegerSorter();
            baseSorter.executeMerge(parameters);
        } else {
            BaseSorter<String> baseSorter = new StringSorter();
            baseSorter.executeMerge(parameters);
        }

    }

}