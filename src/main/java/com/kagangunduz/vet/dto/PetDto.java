package com.kagangunduz.vet.dto;

import com.kagangunduz.vet.entity.Genus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDto {

    private Long id;
    private String name;
    private int age;
    private Genus genus;
    private String description;
    private OwnerDto owner;
}
