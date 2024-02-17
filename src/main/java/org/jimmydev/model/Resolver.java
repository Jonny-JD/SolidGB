package org.jimmydev.model;

import org.jimmydev.dto.RequestDto;
import org.jimmydev.dto.ResponseDto;

public interface Resolver {
    ResponseDto resolve(RequestDto request);

}
