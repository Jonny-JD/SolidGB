package org.jimmydev.dto.impl.complex;

import org.jimmydev.dto.RequestDto;
import org.jimmydev.util.RequestType;

public class RequestComplex implements RequestDto {

    private final RequestType type;
    private final String request;

    public RequestComplex(String request, RequestType type) {
        this.type = type;
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    @Override
    public RequestType getType() {
        return type;
    }
}
