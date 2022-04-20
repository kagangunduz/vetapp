package com.kagangunduz.vet.dto;

import com.kagangunduz.vet.entity.Genus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {
    private Long id;
    @NotEmpty(message = "İsim alanı boş olamaz.")
    private String name;
    @NotNull(message = "Doğum Tarihi boş olamaz.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotNull(message = "Cins alanı boş olamaz.")
    private Genus genus;
    private String species;
    private String description;
    private OwnerDto owner;
    private Date createdAt;
    private Date updatedAt;
}
