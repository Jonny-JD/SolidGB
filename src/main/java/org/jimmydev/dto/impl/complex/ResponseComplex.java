package org.jimmydev.dto.impl.complex;

import org.jimmydev.dto.ResponseDto;
import org.jimmydev.util.State;
import org.jimmydev.entity.ComplexNumber;

public class ResponseComplex implements ResponseDto {
    private final State state;
    private final String message;
    private final ComplexNumber complexNumber;

    public String getMessage() {
        return message;
    }

    public ComplexNumber getComplexNumber() {
        return complexNumber;
    }

    public ResponseComplex(State state) {
        this.state = state;
        this.message = "";
        this.complexNumber = null;
    }

    public ResponseComplex(State state, String message) {
        this.state = state;
        this.message = message;
        this.complexNumber = null;
    }

    public ResponseComplex(State state, ComplexNumber complexNumber) {
        this.state = state;
        this.complexNumber = complexNumber;
        this.message = "";
    }

    @Override
    public State getState() {
        return state;
    }
}
