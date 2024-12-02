package com.example.portfolio.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getList(){
         return this.itemRepository.findAll();
    }

    public Item getItem(Long id){
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()){
            return item.get();
        } else {
            throw new RuntimeException();
        }
    }

}
