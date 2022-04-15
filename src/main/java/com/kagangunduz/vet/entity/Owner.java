package com.kagangunduz.vet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Ad Soyad alanı boş olamaz.")
    private String fullName;

    @Column(name = "telephone_number")
    @NotEmpty(message = "Telefon alanı boş olamaz.")
    private String telephoneNumber;


    @Email(message = "Email adresi geçerli değil.")
    @NotEmpty(message = "Email alanı boş olamaz.")
    private String email;

    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @OrderBy("id DESC")
    private List<Pet> pets = new ArrayList<>();

}
