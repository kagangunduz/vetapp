package com.kagangunduz.vet.dto;

import com.kagangunduz.vet.entity.Genus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
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
    @NotNull(message = "Yaş alanı boş olamaz.")
    @Range(min = 0, max = 200, message = "Yaş alanı 0 ile 200 arasında olmalıdır.")
    private int age;
    @NotNull(message = "Cins alanı boş olamaz.")
    private Genus genus;
    private String description;
    private OwnerDto owner;
    private Date createdAt;
    private Date updatedAt;
}
