package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
}
