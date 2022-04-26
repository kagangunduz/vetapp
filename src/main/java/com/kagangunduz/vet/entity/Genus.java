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
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "genus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;

    @OneToMany(mappedBy = "genus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Species> species;

    @Override
    public String toString() {
        return "Genus{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
