package com.household.code.controller;

import com.household.code.entity.Purchase;
import com.household.code.model.purchase.PurchaseReportResponse;
import com.household.code.model.purchase.PurchaseRequest;
import com.household.code.model.purchase.PurchaseResponse;
import com.household.code.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/household/purchase")
@Log4j2
@Tag(name = "Purchase", description = "API for managing household purchases.")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/getPurchases")
    @Operation(summary = "Get all purchases", description = "Returns a list of all purchases.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of purchases"),
            @ApiResponse(responseCode = "404", description = "No purchases found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<PurchaseResponse> getPurchases() {
        try {
            PurchaseResponse purchaseResponse = purchaseService.getAllPurchases();
            if (purchaseResponse.getPurchaseList().isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            log.info("Purchase Response Size : {}", purchaseResponse.getPurchaseList().size());
            return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Internal Server Error: {}", exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPurchase/{id}")
    @Operation(summary = "Get a purchase by ID", description = "Returns the details of a specific purchase identified by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the purchase"),
            @ApiResponse(responseCode = "404", description = "Purchase not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        try {
            Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
            if (purchase.isPresent()) {
                return new ResponseEntity<>(purchase.get(), HttpStatus.OK);
            } else {
                throw new NoSuchElementException("No purchase found under the id: " + id);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addPurchase")
    @Operation(summary = "Add a new purchase", description = "Creates a new purchase entry.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase successfully created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Long> addPurchase(@RequestBody PurchaseRequest purchaseRequest) {
        try {
            log.info(purchaseRequest);
            Long entryResult = purchaseService.addPurchase(purchaseRequest);
            if(entryResult==0L){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(entryResult, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPurchaseReport")
    public ResponseEntity<PurchaseReportResponse> getPurchaseReport(
            @RequestParam(value = "days", defaultValue = "1") Integer days) {
        try {
            PurchaseReportResponse purchaseReportResponse = purchaseService.getPurchaseReport(days);
            return new ResponseEntity<>(purchaseReportResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
