package com.household.code.util;

import com.household.code.entity.Purchase;
import com.household.code.model.purchase.PurchaseReportRequest;
import com.household.code.model.purchase.PurchaseReportResponse;
import com.household.code.model.purchase.PurchaseRequest;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Log4j2
public class PurchaseServiceUtil {

    public static Purchase mapRequestToEntity(PurchaseRequest purchaseRequest) {
        return Purchase.builder()
                .mass(purchaseRequest.getMass())
                .type(purchaseRequest.getType().toUpperCase())
                .quantity(purchaseRequest.getQuantity())
                .note(purchaseRequest.getNote())
                .purchaseAmount(purchaseRequest.getAmount())
                .itemName(purchaseRequest.getProductName())
                .purchaseDate(new Date())
                .build();
    }

    public static PurchaseReportRequest getFromAndToDateByDays(int daysAgo){
        // Get today's date
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        // Calculate the date n days ago
        LocalDate oldDate = today.minusDays(daysAgo);
        Date toDate = Date.from(now.toInstant(ZoneOffset.UTC));
        //Date toDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date fromDate = Date.from(oldDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println("test");
        return new PurchaseReportRequest(fromDate, toDate);
    }

    public static PurchaseReportResponse mapResultToPurchaseReport(List<Purchase> purchaseDetails) {
        PurchaseReportResponse purchaseReportResponse = new PurchaseReportResponse();
        Double totalAmount = purchaseDetails.stream().map(Purchase::getPurchaseAmount).reduce(0.0,Double::sum);
        Map<String, Double> categorySummary = purchaseDetails.stream()
                .collect(Collectors.groupingBy(Purchase::getType,Collectors.summingDouble(Purchase::getPurchaseAmount)));
        purchaseReportResponse.setNoOfOrders(purchaseDetails.size());
        purchaseReportResponse.setTotalAmount(totalAmount);
        purchaseReportResponse.setCategorySummary(categorySummary);
        return purchaseReportResponse;

    }
}
