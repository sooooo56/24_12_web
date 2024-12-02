package com.example.portfolio.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/list")
    public String itemList(Model model){
        List<Item> itemList = this.itemService.getList();
        model.addAttribute("itemList",itemList);

        return "list";
    }

}
