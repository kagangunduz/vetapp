package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpeciesRepository extends JpaRepository<Species, Long> {

    List<Species> getAllByGenusId(Long genusId);

}
