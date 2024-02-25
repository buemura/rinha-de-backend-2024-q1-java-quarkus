package com.buemura.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record BalanceDto(
        Integer total,

        @JsonProperty("data_extracao")
        Date requestedDate,

        @JsonProperty("limite")
        Integer limit
) {
}
