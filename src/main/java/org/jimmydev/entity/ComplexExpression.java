package org.jimmydev.entity;

import org.jimmydev.util.Operator;

public class ComplexExpression {
    private ComplexNumber firstNumber;
    private ComplexNumber secondNumber;
    private Operator operator;
    public ComplexExpression(){

    }

    public ComplexExpression(ComplexNumber firstNumber, ComplexNumber secondNumber, Operator operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    public ComplexNumber getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(ComplexNumber firstNumber) {
        this.firstNumber = firstNumber;
    }

    public ComplexNumber getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(ComplexNumber secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
