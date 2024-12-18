package com.example.portfolio.order.entity;

import com.example.portfolio.Member.Member;
import com.example.portfolio.item.Item;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private String color;
    private String size;
    private int quantity;

    private LocalDateTime orderDate;

    private String deliveryAddress;
    private String phoneNumber;
}
