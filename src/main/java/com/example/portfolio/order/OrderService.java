package com.example.portfolio.order;

import com.example.portfolio.item.Item;
import com.example.portfolio.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public Item getItemId(Long id){
        return itemService.getItem(id);
    }

    public Order createOrder(Long id, String color, String size, int quantity,
                             String deliveryAddress, String phoneNumber) {
        Item item = itemService.getItem(id);
        if (item == null) {
            throw new IllegalArgumentException("유효하지 않은 상품 ID입니다.");
        }

        Order order = Order.builder()
//                .item(item)
//                .color(color)
                .size(size)
                .quantity(quantity)
                .deliveryAddress(deliveryAddress)
                .phoneNumber(phoneNumber)
                .orderDate(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }

}
