package com.buemura.controllers;

import com.buemura.exceptions.CustomerNotFoundException;
import com.buemura.exceptions.InvalidArgumentException;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import com.buemura.dtos.StatementDto;
import com.buemura.services.StatementService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/clientes/{id}/extrato")
public class StatementController {
    @Inject
    StatementService statementService;

    @GET
    @RunOnVirtualThread
    public RestResponse<StatementDto> getStatement(@PathParam("id") String id) {
        Integer customerId = parseAndValidateCustomerId(id);
        return RestResponse.ok(statementService.getStatement(customerId));
    }

    private Integer parseAndValidateCustomerId(String id) {
        int customerId;

        try {
            customerId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }

        if (customerId < 1 || customerId > 5)  throw new CustomerNotFoundException();
        return customerId;
    }
}
