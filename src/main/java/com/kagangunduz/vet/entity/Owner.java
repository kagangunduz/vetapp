package com.kagangunduz.vet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Ad Soyad alanı boş olamaz.")
    private String fullName;

    @Column(name = "telephone_number")
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    private String telephoneNumber;

    @Column(unique = true)
    @Email(message = "Email adresi geçerli değil.")
    @NotEmpty(message = "Email alanı boş olamaz.")
    private String email;

    private String address;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<Pet> pets;


    @PreRemove
    private void preRemove() {
        pets.forEach(pet -> pet.setOwner(null));
    }

}
