package com.kagangunduz.vet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String email;
    private String address;

    @OneToMany
    @JoinColumn(name = "owner_id")
    private List<Pet> pets;
}
