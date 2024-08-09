package com.household.code.service;

import com.household.code.entity.Purchase;
import com.household.code.model.purchase.PurchaseReportRequest;
import com.household.code.model.purchase.PurchaseReportResponse;
import com.household.code.model.purchase.PurchaseRequest;
import com.household.code.model.purchase.PurchaseResponse;
import com.household.code.repository.PurchaseRepository;
import com.household.code.util.PurchaseServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.household.code.util.PurchaseServiceUtil.mapRequestToEntity;

@Service
@Log4j2
public class PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    public PurchaseResponse getAllPurchases(){
        try{
            List<Purchase> purchaseList = purchaseRepository.findAll();
            return new PurchaseResponse(purchaseList);
        }catch (Exception e){
            return null;
        }
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        try{
            return purchaseRepository.findById(id);
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public Long addPurchase(PurchaseRequest purchaseRequest) {
        try{
            Purchase entry = mapRequestToEntity(purchaseRequest);
            Long id =  purchaseRepository.save(entry).getPurchaseId();
            return id;
        }catch (Exception e){
            return 0L;
        }
    }
    public PurchaseReportResponse getPurchaseReport(Integer days) {
        PurchaseReportRequest purchaseReportRequest = PurchaseServiceUtil.getFromAndToDateByDays(days);
        List<Purchase> purchasesByDateRange = purchaseRepository.findPurchasesByDateRange(purchaseReportRequest.getFromDate(),purchaseReportRequest.getToDate());
        return PurchaseServiceUtil.mapResultToPurchaseReport(purchasesByDateRange);
    }



}
