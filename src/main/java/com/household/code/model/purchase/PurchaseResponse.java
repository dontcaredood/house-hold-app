package com.household.code.model.purchase;

import com.household.code.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PurchaseResponse {
    List<Purchase> purchaseList;
}
