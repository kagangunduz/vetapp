package com.kagangunduz.vet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"pets"})
@EqualsAndHashCode(callSuper = true)
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

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Pet> pets = new ArrayList<>();
}
