package com.household.code.service;

import com.household.code.entity.Toiletries;
import com.household.code.repository.ToiletriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletriesService {
    @Autowired
    ToiletriesRepository toiletriesRepository;

    public List<Toiletries> getAllToiletries(){
        return toiletriesRepository.findAll();
    }
}
