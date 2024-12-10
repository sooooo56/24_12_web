package com.example.portfolio.order;

import com.example.portfolio.item.Item;
import com.example.portfolio.item.ItemRepository;
import com.example.portfolio.item.ItemService;
import lombok.RequiredArgsConstructor;
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
            , @RequestParam String quantity, Model model, @RequestParam Long id){

        Item item = orderService.getItemId(id);

        if (item == null){
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }

        model.addAttribute("size",size);
        model.addAttribute("color",color);
        model.addAttribute("quantity",quantity);
        model.addAttribute("item",item);

        return "order_form";
    }

    @PostMapping("/order_submit")
    public String submitOrder( @RequestParam Long id,
                               @RequestParam String color,
                               @RequestParam String size,
                               @RequestParam int quantity,
                               @RequestParam String deliveryAddress,
                               @RequestParam String phoneNumber){

        Order order = orderService.createOrder(id, color, size, quantity, deliveryAddress, phoneNumber);

        return "redirect:/order_success";
    }

    @GetMapping("/order_success")
    public String submitOrder() {

        return "order_success";
    }

}
