package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.exception.GenusNotFoundException;
import com.kagangunduz.vet.repository.GenusRepository;
import com.kagangunduz.vet.service.GenusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GenusServiceImpl implements GenusService {

    private final GenusRepository genusRepository;

    @Override
    public Genus save(Genus genus) {
        genus.setName(genus.getName().toLowerCase());
        return genusRepository.save(genus);
    }

    @Override
    public Genus findById(Long id) {
        Genus genus = genusRepository.findById(id).orElseThrow(
                () -> new GenusNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
        return genus;
    }

    @Override
    public Genus update(Long id, Genus genus) {
        Optional<Genus> optionalGenus = genusRepository.findById(id);
        if (optionalGenus.isPresent()) {
            Genus genusDb = optionalGenus.get();
            genusDb.setName(genus.getName().toLowerCase());
            return genusRepository.save(genus);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    @Override
    public Genus deleteById(Long id) {
        Optional<Genus> optionalGenus = genusRepository.findById(id);
        if (optionalGenus.isPresent()) {
            genusRepository.deleteById(id);
            return optionalGenus.get();
        } else {
            throw new GenusNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }

    @Override
    public List<Genus> findAll() {
        return genusRepository.findAll(Sort.by("name"));
    }

    @Override
    public Long getGenusCount() {
        return genusRepository.count();
    }

}
