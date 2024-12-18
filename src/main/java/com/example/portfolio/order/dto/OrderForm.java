package com.example.portfolio.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderForm {
    private Long id;
    private String color;
    private String size;
    private int quantity;
    private String deliveryAddress;
    private String phoneNumber;
    
    // 결제 금액 계산을 위한 메서드
    public Long calculatePrice(Long itemPrice) {
        return itemPrice * quantity;
    }
} 