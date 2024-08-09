package com.household.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "toiletries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class Toiletries {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "toiletries_sequence")
    @SequenceGenerator(name = "toiletries_sequence", sequenceName = "toiletries_sequence", initialValue= 1, allocationSize = 1)
    private long toiletriesId;

    @Column(name = "toiletry_name")
    private String toiletryName;

    @Column(name = "toiletry_type")
    private String toiletryType;
}
