package com.buemura.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record StatementDto(
        @JsonProperty("saldo")
        BalanceDto balance,

        @JsonProperty("ultimas_transacoes")
        List<TransactionDto> RecentTransactions
) {
}
