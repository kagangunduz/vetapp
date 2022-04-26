package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Species;

import java.util.List;

public interface SpeciesService {

    Species save(Species species);

    Species findById(Long id);

    Species update(Long id, Species species);

    Species deleteById(Long id);

    List<Species> findAll();

    Long getSpeciesCount();

    List<Species> getAllByGenusId(Long genusId);
}
