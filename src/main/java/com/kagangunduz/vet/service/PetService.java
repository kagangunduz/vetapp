package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Pet;

import java.awt.print.Pageable;

public interface PetService {

    Pet save(Pet pet);

    Pet getById(Long id);


}
