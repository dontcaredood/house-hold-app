package com.household.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "groceries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class Groceries {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groceries_sequence")
    @SequenceGenerator(name = "groceries_sequence", sequenceName = "groceries_sequence", initialValue= 1, allocationSize = 1)
    private long groceriesId;

    @Column(name = "grocery_name")
    private String groceryName;

    @Column(name = "grocery_type")
    private String groceryType;

}
