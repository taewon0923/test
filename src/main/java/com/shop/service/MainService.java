package com.shop.service;

import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

    private final ItemRepository itemRepository;

    public void getMainItemPage(){

    }

}
