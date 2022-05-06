package com.kagangunduz.vet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    @NotEmpty(message = "Ad alanı boş olamaz")
    private String name;

    @OneToMany(mappedBy = "genus", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Pet> pets;

    @OneToMany(mappedBy = "genus", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Species> species;

    @PreRemove
    private void preRemove() {
        pets.forEach((pet) -> {
            pet.setGenus(null);
            pet.setSpecies(null);
        });
    }

    @Override
    public String toString() {
        return "Genus{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
