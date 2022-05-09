package com.kagangunduz.vet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "genus_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Species implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ad alanı boş olamaz.")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genus_id")
    @NotNull(message = "Cins alanı boş olamaz.")
    @JsonBackReference
    private Genus genus;

    @OneToMany(mappedBy = "species", fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Pet> pets;

    @PreRemove
    private void preRemove() {
        pets.forEach(pet -> pet.setSpecies(null));
    }

    @Override
    public String toString() {
        return "Species{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
