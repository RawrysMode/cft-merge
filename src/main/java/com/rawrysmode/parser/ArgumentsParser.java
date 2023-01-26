package com.rawrysmode.parser;

import com.rawrysmode.parser.parameters.Parameters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentsParser {

    private static final StringBuilder ILLEGAL_ARG_EXCEPTION
            = new StringBuilder("\n\nThe program launch failed. ")
            .append("Something is wrong with the parameters. \n")
            .append("Usage: ")
            .append("java -jar <jarfile> ")
            .append("[-a or -d] <-s or -i> <outputFileName> <inputFiles...> \n")
            .append("\t -a for ascending mergesort (optional)\n")
            .append("\t -d for descending mergesort (optional)\n")
            .append("\t -s for string data format\n")
            .append("\t -i for integer data format")
            .append("\n\nNote that <outputFilename> shouldn't contain any whitespace characters!\n\n");

    private static final IllegalArgumentException illegalArgumentException
            = new IllegalArgumentException(ILLEGAL_ARG_EXCEPTION.toString());

    public static Parameters parse(String[] arguments) {
        if (arguments.length == 0) throw illegalArgumentException;

        String joinedString = String.join(" ", arguments);
        String regularExpression = "((-a|-d)[\\s])?(-s|-i)[\\s](\\S+[.][a-z]+)[\\s](.+[.][a-z]+[\\s]?)+";
        Pattern pattern = Pattern.compile(regularExpression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(joinedString);

        if (matcher.matches() && matcher.group(2) != null) {
            return new Parameters(matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5));
        } else if (matcher.matches() && matcher.group(2) == null) {
            return new Parameters("-a", matcher.group(3), matcher.group(4), matcher.group(5));
        } else throw illegalArgumentException;
    }

}
