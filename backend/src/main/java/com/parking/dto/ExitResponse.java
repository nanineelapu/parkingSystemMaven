package com.parking.dto;

public class ExitResponse {
    private Double amount;
    private String duration;

    public ExitResponse() {}

    public ExitResponse(Double amount, String duration) {
        this.amount = amount;
        this.duration = duration;
    }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
}
