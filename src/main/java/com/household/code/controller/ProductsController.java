package com.household.code.controller;

import com.household.code.entity.Groceries;
import com.household.code.entity.Toiletries;
import com.household.code.model.GroceriesResponse;
import com.household.code.model.ToiletriesResponse;
import com.household.code.service.GroceriesService;
import com.household.code.service.ToiletriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/household/product")
@Log4j2
@Tag(name = "Products", description = "API for managing household products including groceries and toiletries.")
public class ProductsController {

    @Autowired
    GroceriesService groceriesService;

    @Autowired
    ToiletriesService toiletriesService;

    @GetMapping("/hi/{userName}")
    @Operation(summary = "Greet the user", description = "Returns a greeting message for the user.")
    public String sayHi(@PathVariable String userName) {
        return "hi " + userName + ". Welcome to HouseHold application.";
    }

    @GetMapping("/groceries")
    @Operation(summary = "Get all groceries", description = "Returns a list of all groceries with their details.")
    public ResponseEntity<GroceriesResponse> getGroceries() {
        try {
            List<Groceries> allGroceries = groceriesService.getAllGroceries();
            GroceriesResponse groceriesResponse = new GroceriesResponse();

            groceriesResponse.setGroceriesList(allGroceries);
            groceriesResponse.setNoOfProducts((long) allGroceries.size());
            log.info("GroceriesResponse: {}", groceriesResponse);
            if (allGroceries.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(groceriesResponse, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Internal Server Error: {}", exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/toileteries")
    @Operation(summary = "Get all toiletries", description = "Returns a list of all toiletries with their details.")
    public ResponseEntity<ToiletriesResponse> getToiletries() {
        try {
            List<Toiletries> allToiletries = toiletriesService.getAllToiletries();
            ToiletriesResponse toiletriesResponse = new ToiletriesResponse();
            toiletriesResponse.setNoOfProducts((long) allToiletries.size());
            toiletriesResponse.setToiletriesList(allToiletries);
            log.info("ToiletriesResponse: {}", toiletriesResponse);
            if (allToiletries.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(toiletriesResponse, HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Internal Server Error: {}", exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
