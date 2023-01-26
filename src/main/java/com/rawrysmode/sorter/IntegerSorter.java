package com.rawrysmode.sorter;

public class IntegerSorter extends BaseSorter<Integer> {

    @Override
    public Integer parseValue(String string) {
        return Integer.valueOf(string);
    }
}