package com.buemura.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof CustomerNotFoundException) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else if (exception instanceof NotEnoughLimitException || exception instanceof InvalidArgumentException) {
            return Response.status(422).build();
        } else {
            return Response.status(500).build();
        }
    }
}
