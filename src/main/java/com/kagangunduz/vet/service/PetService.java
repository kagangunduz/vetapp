package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.PetDto;

import java.util.List;


public interface PetService {

    List<PetDto> findAll();

    PetDto save(PetDto petDto);

    PetDto findById(Long id);

    PetDto update(Long id, PetDto petDto);

    Boolean deleteById(Long id);

    /*
    Page<Pet> getAllPageable(Pageable pageable);
    */

}
