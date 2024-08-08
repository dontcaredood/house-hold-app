package com.household.code.controller;

import com.household.code.entity.Groceries;
import com.household.code.model.GroceriesResponse;
import com.household.code.service.GroceriesService;
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
public class ProductsController {
    @Autowired
    GroceriesService groceriesService;
    @GetMapping("/hi/{userName}")
    public String sayHi(@PathVariable String userName) {
        return "hi " + userName + ". Welcome to HouseHold application.";
    }

    @GetMapping("/groceries")
    public ResponseEntity<GroceriesResponse> getGroceries() {
        try {
            List<Groceries> allGroceries = groceriesService.getAllGroceries();
            GroceriesResponse groceriesResponse = new GroceriesResponse();
            groceriesResponse.setGroceriesList(allGroceries);
            groceriesResponse.setNoOfProducts((long) allGroceries.size());
            log.info("GroceriesResponse: {}",groceriesResponse);
            if (allGroceries.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(groceriesResponse,HttpStatus.OK);
        } catch (Exception exception) {
            log.error("Internal Server Error: {}",exception.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
