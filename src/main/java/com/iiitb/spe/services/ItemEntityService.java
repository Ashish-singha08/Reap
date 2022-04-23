package com.iiitb.spe.services;

import com.iiitb.spe.model.entities.ItemEntity;
import com.iiitb.spe.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemEntityService {
    @Autowired
    private ItemRepository itemRepository;

    public List<ItemEntity> getAllItems(){
        List<ItemEntity> items = itemRepository.findAll();
        return items;
    }
}
