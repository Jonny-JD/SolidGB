package org.jimmydev.model;

import org.jimmydev.dto.RequestDto;
import org.jimmydev.dto.impl.complex.RequestComplex;
import org.jimmydev.dto.impl.complex.ResponseComplex;
import org.jimmydev.entity.ComplexExpression;
import org.jimmydev.entity.ComplexNumber;
import org.jimmydev.util.ComplexFormatValidator;
import org.jimmydev.util.Parser;

import static org.jimmydev.util.State.FAIL;
import static org.jimmydev.util.State.OK;

public class ComplexResolver implements Resolver {

    private static final ComplexResolver INSTANCE = new ComplexResolver();

    private ComplexResolver() {

    }

    public static ComplexResolver getInstance() {
        return INSTANCE;
    }

    @Override
    public ResponseComplex resolve(RequestDto request) {
        RequestComplex requestComplex = (RequestComplex) request;
        if (ComplexFormatValidator.isValidExpression(requestComplex.getRequest())) {

            ComplexExpression expression = Parser.parseComplexExpression(requestComplex.getRequest());

            switch (expression.getOperator()) {
                case ADDITION -> {
                    return add(expression.getFirstNumber(), expression.getSecondNumber());
                }

                case SUBTRACTION -> {
                    return subtract(expression.getFirstNumber(), expression.getSecondNumber());
                }
                case MULTIPLICATION -> {
                    return multiply(expression.getFirstNumber(), expression.getSecondNumber());
                }
                case DIVISION -> {
                    return divide(expression.getFirstNumber(), expression.getSecondNumber());
                }
            }
        }
        return new ResponseComplex(FAIL);
    }

    private ResponseComplex divide(ComplexNumber firstNumber, ComplexNumber secondNumber) {
        return multiply(firstNumber, reciprocalNumber(secondNumber));
    }

    private ResponseComplex multiply(ComplexNumber firstNumber, ComplexNumber secondNumber) {
        return new ResponseComplex(OK, new ComplexNumber(
                firstNumber.realPart() * secondNumber.realPart() - firstNumber.imaginePart() * secondNumber.imaginePart(),
                firstNumber.realPart() * secondNumber.imaginePart() + firstNumber.imaginePart() * secondNumber.realPart()
        ));
    }

    private ResponseComplex subtract(ComplexNumber firstNumber, ComplexNumber secondNumber) {
        return new ResponseComplex(OK, new ComplexNumber(
                firstNumber.realPart() - secondNumber.realPart(),
                firstNumber.imaginePart() - secondNumber.imaginePart()));
    }

    private ResponseComplex add(ComplexNumber firstNumber, ComplexNumber secondNumber) {
        return new ResponseComplex(OK, new ComplexNumber(
                firstNumber.realPart() + secondNumber.realPart(),
                firstNumber.imaginePart() + secondNumber.imaginePart()));
    }

    private ComplexNumber reciprocalNumber(ComplexNumber number) {
        double scale = Math.pow(number.realPart(), 2) + Math.pow(number.imaginePart(), 2);
        return new ComplexNumber(number.realPart() / scale, -number.imaginePart() / scale );
    }
}
