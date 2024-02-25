package com.buemura.controllers;

import com.buemura.exceptions.CustomerNotFoundException;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import com.buemura.dtos.TransactionRequestDto;
import com.buemura.dtos.TransactionResponseDto;
import com.buemura.exceptions.InvalidArgumentException;
import com.buemura.services.TransactionService;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/clientes/{id}/transacoes")
public class TransactionController {
    @Inject
    TransactionService transactionService;

    @POST
    @Transactional
    @RunOnVirtualThread
    public RestResponse<TransactionResponseDto> createTransaction(@PathParam("id") String id, TransactionRequestDto trx) {
        Integer customerId = parseAndValidateCustomerId(id);
        if (!isValidTransactionPayload(trx)) throw new InvalidArgumentException();

        if (trx.type() == 'c') return RestResponse.ok(transactionService.insertCreditTransaction(customerId, trx));
        return RestResponse.ok(transactionService.insertDebitTransaction(customerId, trx));
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

    private boolean isValidTransactionPayload(TransactionRequestDto trx) {
        int amount;
        try {
            amount = Integer.parseInt(trx.amount());
        } catch (NumberFormatException e) {
            return false;
        }

        if (amount % 2 != 0 && amount % 2 != 1) return false;
        if (trx.type() != 'c' && trx.type() != 'd') return false;
        if (trx.description() == null) return false;
        if (trx.description().isEmpty()) return false;
        if (trx.description().length() > 10) return false;
        return true;
    }
}
