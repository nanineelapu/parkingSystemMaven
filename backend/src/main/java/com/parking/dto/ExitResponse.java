package com.parking.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ExitResponse {
    private Double amount;
    private String duration;
}
