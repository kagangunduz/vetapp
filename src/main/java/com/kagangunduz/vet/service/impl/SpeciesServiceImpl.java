package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Species;
import com.kagangunduz.vet.exception.SpeciesNotFoundException;
import com.kagangunduz.vet.repository.SpeciesRepository;
import com.kagangunduz.vet.service.SpeciesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;

    @Override
    public Species save(Species species) {
        return speciesRepository.save(species);
    }

    @Override
    public Species findById(Long id) {
        return speciesRepository.findById(id).orElseThrow(
                () -> new SpeciesNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
    }

    @Override
    public Species update(Long id, Species species) {
        Optional<Species> optionalGenus = speciesRepository.findById(id);
        if (optionalGenus.isPresent()) {
            Species speciesDb = optionalGenus.get();
            speciesDb.setName(species.getName());
            speciesDb.setGenus(species.getGenus());
            return speciesRepository.save(speciesDb);
        } else {
            throw new SpeciesNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    @Override
    public Species deleteById(Long id) {
        Optional<Species> optionalSpecies = speciesRepository.findById(id);
        if (optionalSpecies.isPresent()) {
            speciesRepository.deleteById(id);
            return optionalSpecies.get();
        } else {
            throw new SpeciesNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }

    @Override
    public List<Species> findAll() {
        return speciesRepository.findAll(Sort.by("name"));
    }

    @Override
    public Long getSpeciesCount() {
        return speciesRepository.count();
    }

    @Override
    public List<Species> getAllByGenusId(Long genusId) {
        try {
            return speciesRepository.getAllByGenusId(genusId);
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

}
