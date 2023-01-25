package com.rawrysmode.parser.parameters;

public class Parameters {

    private final Ordering ordering;
    private final Type type;
    private final String outputFilename;
    private final String[] inputFilenames;

    public Parameters(String ordering, String type, String outputFilename, String inputFilenames) {
        this.ordering = ordering.equals("-a") ? Ordering.ASCENDING : Ordering.DESCENDING;
        this.type = type.equals("-i") ? Type.INTEGER : Type.STRING;
        this.outputFilename = outputFilename;
        this.inputFilenames = inputFilenames.split(" ");
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

    public String[] getInputFilenames() {
        return inputFilenames;
    }
}
