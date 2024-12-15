package com.example.portfolio.order;

import com.example.portfolio.Member.Member;
import com.example.portfolio.item.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderForm {

    @NotBlank
    private String color;
    @NotBlank
    private String size;
    @NotBlank
    private int quantity;
    @NotBlank
    private LocalDateTime orderDate;
    @NotBlank
    private String deliveryAddress;
    @NotBlank
    private String phoneNumber;

}
