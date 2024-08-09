package com.household.code.repository;

import com.household.code.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT p FROM Purchase p WHERE p.purchaseDate BETWEEN :fromDate AND :toDate")
    List<Purchase> findPurchasesByDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
