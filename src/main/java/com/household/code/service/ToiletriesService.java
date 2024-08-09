package com.household.code.service;

import com.household.code.entity.Toiletries;
import com.household.code.repository.ToiletriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToiletriesService {
    @Autowired
    ToiletriesRepository toiletriesRepository;

    public List<Toiletries> getAllToiletries(String sortBy){
        assert sortBy != null;
        Sort sort = Sort.by(Sort.Order.by(sortBy));
        return toiletriesRepository.findAll(sort);
    }
}
