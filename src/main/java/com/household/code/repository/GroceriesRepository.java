package com.household.code.repository;

import com.household.code.entity.Groceries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceriesRepository extends JpaRepository<Groceries, Long> {
}
