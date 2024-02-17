package org.jimmydev.util;

import org.jimmydev.entity.ComplexExpression;
import org.jimmydev.entity.ComplexNumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Parser {
    private static ComplexNumber parseComplexNumber(String number){
        Pattern pattern = Pattern.compile("(?<realPart>-?\\d+\\.?\\d?)\\+(?<imaginePart>\\d+\\.?\\d?)");
        Matcher matcher = pattern.matcher(number);
        matcher.find();

        return new ComplexNumber(
                Double.parseDouble(matcher.group("realPart")),
                Double.parseDouble(matcher.group("imaginePart")) * -1);
    }

    public static ComplexExpression parseComplexExpression(String input){
        ComplexExpression expression = new ComplexExpression();
        Pattern pattern = Pattern.compile
                ("(?<firstNumber>\\(?-?\\d+\\.?\\d?\\+\\d+\\.?\\d?i)\\)?" +
                        "(?<operator>[+\\-*/])" +
                        "(?<secondNumber>\\(?-?\\d+\\.?\\d?\\+\\d+\\.?\\d?i)\\)?");
        Matcher matcher = pattern.matcher(input);

        matcher.find();

        expression.setFirstNumber(parseComplexNumber(matcher.group("firstNumber").replaceAll("[()]","")));
        expression.setSecondNumber(parseComplexNumber(matcher.group("secondNumber").replaceAll("[()]","")));;
        switch (matcher.group("operator")) {
            case "+" -> expression.setOperator(Operator.ADDITION);
            case "-" -> expression.setOperator(Operator.SUBTRACTION);
            case "*" -> expression.setOperator(Operator.MULTIPLICATION);
            case "/" -> expression.setOperator(Operator.DIVISION);
        }

        return expression;
    }
}
