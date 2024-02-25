package com.buemura.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record TransactionDto(
        @JsonProperty("valor")
        Integer amount,
        @JsonProperty("tipo")
        char type,
        @JsonProperty("descricao")
        String description,
        @JsonProperty("realizado_em")
        Date createdAt
) {
}
