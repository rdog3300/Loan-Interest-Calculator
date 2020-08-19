package com.company;

public class RegEx {
    static boolean isDouble(String input)
    {
        return input.matches("[0-9]+.[0-9]+");
    }

    static boolean isDigit(String input)
    {
        return input.matches("[0-9]+");
    }
}
