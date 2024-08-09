package com.household.code.model;

import com.household.code.entity.Toiletries;
import lombok.Data;

import java.util.List;

@Data
public class ToiletriesResponse {
    Long noOfProducts;
    List<Toiletries> toiletriesList;
}
