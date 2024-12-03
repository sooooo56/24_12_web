package com.example.portfolio.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    // 상품 리스트
    public List<Item> getList(){
         return this.itemRepository.findAll();
    }

    // 상품 Id
    public Item getItem(Long id){
        Optional<Item> item = this.itemRepository.findById(id);
        if (item.isPresent()){
            return item.get();
        } else {
            throw new RuntimeException();
        }
    }

}
