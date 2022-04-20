package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;

import java.util.Map;


public interface PetService {

    //PetDto save(PetDto petDto);
    Pet save(Pet pet);

    //PetDto findById(Long id);
    Pet findById(Long id);

    //PetDto update(Long id, PetDto petDto);
    Pet update(Long id, Pet pet);

    //PetDto deleteById(Long id);
    Pet deleteById(Long id);

    //Page<PetDto> getAllPageable(int pageNumber);
    Page<Pet> getAllPageable(int pageNumber);

    //Page<PetDto> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber);
    Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber);

    String getAgeInfo(Long id);

    Long getPetCount();

    Map<Genus, String> getGenusAsHashMap();

}
