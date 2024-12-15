package com.example.portfolio.order;

import com.example.portfolio.Member.Member;
import com.example.portfolio.Member.MemberService;
import com.example.portfolio.item.Item;
import com.example.portfolio.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ItemService itemService;
    private final MemberService memberService;

    public Item getItemId(Long id){
        return itemService.getItem(id);
    }

    public Order createOrder(Member member, Long id, String color, String size, int quantity,
                             String deliveryAddress, String phoneNumber) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
        }

        if (deliveryAddress == null || deliveryAddress.isBlank()) {
            throw new IllegalArgumentException("배송 주소를 입력하세요.");
        }

        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("전화번호를 입력하세요.");
        }

        Item item = itemService.getItem(id);
        if (item == null) {
            throw new IllegalArgumentException("유효하지 않은 상품 ID입니다.");
        }

        Order order = Order.builder()
                .member(member)
                .item(item)
                .color(color)
                .size(size)
                .quantity(quantity)
                .deliveryAddress(deliveryAddress)
                .phoneNumber(phoneNumber)
                .orderDate(LocalDateTime.now())
                .build();

        return orderRepository.save(order);
    }


}
