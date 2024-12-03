package com.example.portfolio.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/detail/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        Item item = this.itemService.getItem(id);
        model.addAttribute("item",item);

        return "detail";
    }

}
