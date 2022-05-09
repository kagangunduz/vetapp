package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Genus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenusRepository extends JpaRepository<Genus, Long> {

    List<Genus> getAllByName(String name);

}
