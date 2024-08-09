package com.household.code.model.purchase;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseRequest {
    private String productName;
    private String type;
    private Integer quantity;
    private Double mass;
    private Double amount;
    private String note;
}
