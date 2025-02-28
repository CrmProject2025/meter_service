package com.example.meter2_service.dtos;

public class KafkaDto {
    private String title;
    private Integer quantity;

    public KafkaDto(String title, Integer quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public KafkaDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
