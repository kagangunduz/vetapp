package com.kagangunduz.vet.dto;

import com.kagangunduz.vet.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

    private Long id;
    @NotEmpty(message = "Ad Soyad alanı boş olamaz.")
    private String fullName;
    private String telephoneNumber;
    @NotEmpty(message = "Email alanı boş olamaz.")
    @Email(message = "Email adresi geçersiz.")
    private String email;
    private String address;
    private List<PetDto> pets;
    private Date createdAt;
    private Date updatedAt;

}
