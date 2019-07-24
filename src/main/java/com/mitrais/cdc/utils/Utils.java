package com.mitrais.cdc.utils;

import java.util.regex.Pattern;

public final class Utils {
    public final static Boolean isLengthValid(String input) {
        boolean isValid = input.length() == 6;
        return isValid;
    }

    public final static Boolean isNumericOnlyValid(String input) {
        boolean isValid = Pattern.matches("\\d+", input);

        return isValid;
    }
}