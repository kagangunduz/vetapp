package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;

import java.util.List;


public interface PetService {

    List<Pet> findAll();

    Page<Pet> getAllPageable(int pageNumber);

    Pet save(Pet pet);

    Pet findById(Long id);

    Pet update(Long id, Pet pet);

    Boolean deleteById(Long id);

}
