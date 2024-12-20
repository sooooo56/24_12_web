package com.example.portfolio.order.controller;

import com.example.portfolio.Member.Member;
import com.example.portfolio.item.Item;
import com.example.portfolio.order.dto.OrderForm;
import com.example.portfolio.order.entity.Order;
import com.example.portfolio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final OrderService orderService;
    @Autowired
    private WidgetController widgetController;

    @GetMapping("/checkout")
    public String checkout(OrderForm orderForm,
                           @AuthenticationPrincipal Member member,
                           Model model) {
        try {
//            if (member == null) {
//                return "redirect:/login";
//            }

            Item item = orderService.getItemId(orderForm.getId());
            if (item == null) {
                return "redirect:/global/error";
            }
            
            Long price = orderForm.calculatePrice(item.getPrice());
            
            // 모델에 데이터 추가
            model.addAttribute("item", item);
            model.addAttribute("price", price);
            model.addAttribute("member", member);
            model.addAttribute("orderForm", orderForm);
            
            return "order/checkout";
            
        } catch (Exception e) {
            log.error("Checkout error: {}", e.getMessage(), e);
            return "redirect:/global/error";
        }
    }

    @GetMapping("/success")
    public String paymentSuccess(@RequestParam String paymentKey,
                               @RequestParam String orderId,
                               @RequestParam Long amount,
                               @AuthenticationPrincipal Member member,
                               @ModelAttribute OrderForm orderForm,
                               Model model) {
        try {
            // 결제 성공 시 주문 생성
            Order order = orderService.createOrder(
                member,
                orderForm.getId(),
                orderForm.getColor(),
                orderForm.getSize(),
                orderForm.getQuantity(),
                orderForm.getDeliveryAddress(),
                orderForm.getPhoneNumber()
            );

            model.addAttribute("order", order);
            model.addAttribute("member", member);
            model.addAttribute("orderForm", orderForm);
            model.addAttribute("amount", amount);
            model.addAttribute("paymentKey", paymentKey);
            model.addAttribute("orderId", orderId);
            
            return "order/success";

        } catch (Exception e) {
            log.error("Payment success handling error: {}", e.getMessage());
            return "redirect:/order/fail";
        }
    }

    @GetMapping("/order/fail")
    public String paymentFail(@RequestParam(required = false) String message,
                            @RequestParam(required = false) String code,
                            Model model) {
        model.addAttribute("message", message);
        model.addAttribute("code", code);
        return "order/fail";
    }
    


} 