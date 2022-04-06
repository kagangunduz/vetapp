package com.kagangunduz.vet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Genus  genus;

    @Column(columnDefinition = "TEXT")
    private String description;

    @JoinColumn(name = "owner_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Owner owner;

}
