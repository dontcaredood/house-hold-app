package com.household.code.model.purchase;

import lombok.Data;

import java.util.Map;

@Data
public class PurchaseReportResponse {
    private int noOfOrders;
    private double totalAmount;
    private Map<String,Double> categorySummary;

}
