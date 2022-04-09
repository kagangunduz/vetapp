package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.PetDto;
import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PetService {

    Pet save(Pet pet);

    Pet findById(Long id);

    Pet updateById(Long id, Pet pet);

    void deleteById(Long id);

    Page<Pet> getAllPageable(Pageable pageable);

}
