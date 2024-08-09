package com.household.code.model;

import com.household.code.entity.Groceries;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
//@Builder
public class GroceriesResponse {
    Long noOfProducts;
    List<Groceries> groceriesList;
}
