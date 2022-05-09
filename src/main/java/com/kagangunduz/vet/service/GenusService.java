package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Genus;

import java.util.List;

public interface GenusService {

    Genus save(Genus genus);

    Genus findById(Long id);

    Genus update(Long id, Genus genus);

    Genus deleteById(Long id);

    List<Genus> findAll();

    Long getGenusCount();
}
