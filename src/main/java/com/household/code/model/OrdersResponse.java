package com.household.code.model;

import com.household.code.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrdersResponse {
    long noOfElements;
    List<Orders> ordersList;
}
