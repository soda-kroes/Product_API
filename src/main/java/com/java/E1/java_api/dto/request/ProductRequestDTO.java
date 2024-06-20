package com.java.E1.java_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price_per_unit")
    private Double pricePerUnit;

    @JsonProperty("active_for_sell")
    private boolean activeForSell;
}
