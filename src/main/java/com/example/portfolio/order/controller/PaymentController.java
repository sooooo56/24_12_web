package com.example.portfolio.order.controller;

import com.example.portfolio.Member.Member;
import com.example.portfolio.item.Item;
import com.example.portfolio.order.dto.OrderForm;
import com.example.portfolio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final OrderService orderService;

    @GetMapping("/checkout")
    public String checkout(OrderForm orderForm,
                           @AuthenticationPrincipal Member member,
                           Model model) {
        try {
            // 상품 조회 및 결제 금액 계산
            Item item = orderService.getItemId(orderForm.getId());
            Long price = orderForm.calculatePrice(item.getPrice());
            
            // 모델에 데이터 추가
            addAttributesToModel(model, item, price, member, orderForm);
            
            return "payments/checkout";
            
        } catch (Exception e) {
            log.error("Checkout error: {}", e.getMessage());
            return "redirect:/global/error";
        }
    }

    @GetMapping("/payment/success")
    public String paymentSuccess(OrderForm orderForm,
                                 @AuthenticationPrincipal Member member) {
        try {
            // 결제 성공 시 주문 생성
            orderService.createOrder(
                member,
                orderForm.getId(),
                orderForm.getColor(),
                orderForm.getSize(),
                orderForm.getQuantity(),
                orderForm.getDeliveryAddress(),
                orderForm.getPhoneNumber()
            );
            return "redirect:/order/success";
        } catch (Exception e) {
            log.error("Payment success handling error: {}", e.getMessage());
            return "redirect:/order/fail";
        }
    }

    @GetMapping("/fail")
    public String paymentFail() {
        return "payments/fail";
    }

    // 모델 속성 추가를 위한 private 메서드
    private void addAttributesToModel(Model model, Item item, Long price,
                                    Member member, OrderForm orderForm) {
        model.addAttribute("item", item);
        model.addAttribute("price", price);
        model.addAttribute("member", member);
        model.addAttribute("checkoutRequest", orderForm);
    }

} 