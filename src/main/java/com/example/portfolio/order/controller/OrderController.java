package com.example.portfolio.order.controller;

import com.example.portfolio.Member.Member;
import com.example.portfolio.item.Item;
import com.example.portfolio.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("order_form")
    public String orderForm(@RequestParam String color, @RequestParam String size
            , @RequestParam int quantity, Model model, @RequestParam Long id,@AuthenticationPrincipal Member member){

        Item item = orderService.getItemId(id);

        if (item == null){
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        Long price = item.getPrice() * (long)quantity;
        model.addAttribute("size",size);
        model.addAttribute("color",color);
        model.addAttribute("quantity",quantity);
        model.addAttribute("item",item);
        model.addAttribute("price",price);
        model.addAttribute("member", member);

        return "/order/order_form";
    }





}
