package com.kagangunduz.vet.dto;

import com.kagangunduz.vet.entity.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {

    private Long id;
    private String fullName;
    private String telephoneNumber;
    private String email;
    private String address;
    private List<Pet> pets;
    private Date createdAt;
    private Date updatedAt;

}
