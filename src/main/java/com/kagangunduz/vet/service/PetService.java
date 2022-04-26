package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;


public interface PetService {

    Pet save(Pet pet);

    Pet findById(Long id);

    Pet update(Long id, Pet pet);

    Pet deleteById(Long id);

    Page<Pet> getAllPageable(int pageNumber);

    Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber);

    Long getPetCount();

    String getAgeInfo(Long id);

}
