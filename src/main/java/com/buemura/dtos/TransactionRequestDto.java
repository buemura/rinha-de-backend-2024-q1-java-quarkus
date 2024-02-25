package com.buemura.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequestDto(
        @JsonProperty("valor")
        String amount,

        @JsonProperty("tipo")
        Character type,

        @JsonProperty("descricao")
        String description
) {
}
