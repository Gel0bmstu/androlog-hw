package com.example.numberslist;

import java.util.ArrayList;
import java.util.List;

public class NumbersData {
    private final List<Integer> numbers;
    private static NumbersData src;

    private NumbersData() {

        numbers = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            numbers.add(i);
        }
    }

    public synchronized static NumbersData getInstance() {
        if (src == null) {
            src = new NumbersData();
        }
        return src;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
