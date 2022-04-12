package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findAllByFullNameContainsIgnoreCase(String fullName);

}
