package com.api.fruit.utils;

import com.api.fruit.models.Identifiable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidatorsUtils {

    public static void checkIdNotPresent(Identifiable obj) {
        if (obj.hasId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot specify an ID when creating or updating a fruit object.");
        }
    }
}
