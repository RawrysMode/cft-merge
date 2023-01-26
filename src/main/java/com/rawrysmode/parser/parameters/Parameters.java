package com.rawrysmode.parser.parameters;

import com.rawrysmode.utilities.Deserializer;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Parameters {

    private final Ordering ordering;
    private final Type type;
    private final String outputFilename;
    private final ArrayList<BufferedReader> bufferedReaders;

    public Parameters(String ordering, String type, String outputFilename, String inputFilenames) {
        this.ordering = ordering.equals("-a") ? Ordering.ASCENDING : Ordering.DESCENDING;
        this.type = type.equals("-i") ? Type.INTEGER : Type.STRING;
        this.outputFilename = outputFilename;
        this.bufferedReaders = Deserializer.initializeBufferedReaders(inputFilenames.split(" "));
    }

    public Ordering getOrdering() {
        return ordering;
    }

    public Type getType() {
        return type;
    }

    public String getOutputFilename() {
        return outputFilename;
    }

    public ArrayList<BufferedReader> getBufferedReaders() {
        return bufferedReaders;
    }
}
