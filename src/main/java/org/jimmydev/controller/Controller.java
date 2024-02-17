package org.jimmydev.controller;

import org.jimmydev.dto.impl.complex.RequestComplex;
import org.jimmydev.dto.impl.complex.ResponseComplex;
import org.jimmydev.model.ComplexResolver;
import org.jimmydev.model.Resolver;
import org.jimmydev.util.RequestType;
import org.jimmydev.util.State;

import static org.jimmydev.util.State.FAIL;

public class Controller {
    private static final Controller INSTANCE = new Controller();
    private Resolver resolver;

    private Controller() {
    }

    public static Controller getInstance() {
        return INSTANCE;
    }

    public ResponseComplex request(RequestComplex request) {

        if (request.getRequest().equalsIgnoreCase("exit")) return new ResponseComplex(State.STOP);

        if (request.getType() == RequestType.COMPLEX_EXPRESSION) {
            resolver = ComplexResolver.getInstance();
            return (ResponseComplex) resolver.resolve(request);
        }

        return new ResponseComplex(FAIL);
    }

}
