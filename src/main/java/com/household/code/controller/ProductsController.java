package com.household.code.controller;

import com.household.code.entity.Groceries;
import com.household.code.entity.Toiletries;
import com.household.code.model.products.GroceriesResponse;
import com.household.code.model.products.ToiletriesResponse;
import com.household.code.service.GroceriesService;
import com.household.code.service.ToiletriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/household/product")
@CrossOrigin("*")
@Log4j2
@Tag(name = "Products", description = "API for managing household products including groceries and toiletries.")
public class ProductsController {

    @Autowired
    GroceriesService groceriesService;

    @Autowired
    ToiletriesService toiletriesService;

    @GetMapping("/hi/{userName}")
    @Operation(summary = "Greet the user", description = "Returns a greeting message for the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Greeting message successfully returned"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public String sayHi(@PathVariable String userName) {
        return "hi " + userName + ". Welcome to HouseHold application.";
    }

    @GetMapping("/groceries")
    @Operation(summary = "Get all groceries", description = "Returns a list of all groceries with their details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of groceries"),
            @ApiResponse(responseCode = "404", description = "No groceries found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<GroceriesResponse> getGroceries(@RequestParam(required = false,defaultValue = "groceryName") String sortBy) {
        try {
            log.info("Sort By : {}",sortBy);
            List<Groceries> allGroceries = groceriesService.getAllGroceries(sortBy);
            GroceriesResponse groceriesResponse = new GroceriesResponse();

            groceriesResponse.setGroceriesList(allGroceries);
            groceriesResponse.setNoOfProducts((long) allGroceries.size());
            log.info("GroceriesResponse: {}", allGroceries.size());
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of toiletries"),
            @ApiResponse(responseCode = "404", description = "No toiletries found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<ToiletriesResponse> getToiletries(@RequestParam(required = false, defaultValue = "toiletryName") String sortBy) {
        try {
            List<Toiletries> allToiletries = toiletriesService.getAllToiletries(sortBy);
            ToiletriesResponse toiletriesResponse = new ToiletriesResponse();
            toiletriesResponse.setNoOfProducts((long) allToiletries.size());
            toiletriesResponse.setToiletriesList(allToiletries);
            log.info("ToiletriesResponse: {}", toiletriesResponse.getToiletriesList().size()    );
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
