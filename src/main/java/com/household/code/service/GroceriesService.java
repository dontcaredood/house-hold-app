package com.household.code.service;

import com.household.code.entity.Groceries;
import com.household.code.repository.GroceriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceriesService {
    @Autowired
    GroceriesRepository groceriesRepository;

    public List<Groceries> getAllGroceries(){
        return groceriesRepository.findAll();
    }
}
