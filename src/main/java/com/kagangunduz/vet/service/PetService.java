package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Pet;

import java.util.List;


public interface PetService {

    List<Pet> findAll();

    Pet save(Pet pet);

    Pet findById(Long id);

    Pet update(Long id, Pet pet);

    /*
    Boolean deleteById(Long id);
    */

    /*
    void deleteById(Long id);
    Page<Pet> getAllPageable(Pageable pageable);
    */

}
