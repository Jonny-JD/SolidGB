package org.jimmydev.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ComplexFormatValidator {
    public static boolean isValidExpression(String expression) {
        Pattern pattern = Pattern.compile
                ("(?<firstNumber>\\(?-?\\d+\\.?\\d?\\+\\d+\\.?\\d?i)\\)?" +
                        "(?<operator>[+\\-*/])" +
                        "(?<secondNumber>\\(?-?\\d+\\.?\\d?\\+\\d+\\.?\\d?i)\\)?");
        Matcher matcher = pattern.matcher(expression);
        if (!matcher.find()) {
            return false;
        }

        return true;
    }
}
