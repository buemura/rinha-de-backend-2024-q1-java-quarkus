package com.buemura.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionResponseDto(
        @JsonProperty("limite")
        Integer limit,
        @JsonProperty("saldo")
        Integer balance
) {
}
