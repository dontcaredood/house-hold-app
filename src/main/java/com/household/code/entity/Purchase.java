package com.household.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "purchase")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_sequence")
    @SequenceGenerator(name = "purchase_sequence", sequenceName = "purchase_sequence", initialValue= 1, allocationSize = 1)
    private long purchaseId;
    @Column(name = "item_name",nullable = false)
    private String itemName;
    @Column(name = "type", nullable = false)
    private String type;
    @Column(name = "quantity")
    private long quantity;
    @Column(name = "mass")
    private double mass;
    @Column(name = "purchase_amount", nullable = false)
    private Double purchaseAmount;
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Column(name = "note")
    private String note;
}
