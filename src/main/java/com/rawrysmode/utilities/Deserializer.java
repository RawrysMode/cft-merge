package com.rawrysmode.utilities;

import java.io.*;
import java.util.ArrayList;

public class Deserializer {

    public static ArrayList<File> initializeFiles(String[] inputFilesArray) {
        ArrayList<File> fileArrayList = new ArrayList<>();
        for (String filename : inputFilesArray) {
            File file = new File(filename);
            if (!file.isFile() || file.length() == 0) {
                System.out.println(" > Ignored: " + file.getName() + ". Isn't a file or empty!");
                continue;
            }
            fileArrayList.add(file);
        }
        if (fileArrayList.isEmpty()) throw new RuntimeException("Nothing to sort. Files validation is failed.");
        return fileArrayList;
    }

    public static ArrayList<BufferedReader> initializeBufferedReaders(String[] inputFilesArray) {
        ArrayList<File> fileArrayList = initializeFiles(inputFilesArray);
        ArrayList<BufferedReader> bufferedReaders = new ArrayList<>();
        for (File file : fileArrayList) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
                bufferedReaders.add(bufferedReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bufferedReaders;
    }

}
