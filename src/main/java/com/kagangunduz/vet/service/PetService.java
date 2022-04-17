package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface PetService {

    List<Pet> findAll();

    Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber);

    Page<Pet> getAllPageable(int pageNumber);

    Pet save(Pet pet);

    Pet findById(Long id);

    Pet update(Long id, Pet pet);

    Pet deleteById(Long id);

    Long getPetCount();

    String getAgeInfo(Long id);

    Map<Genus, String> getGenusAsHashMap();

}
