package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.exception.GenusNotFoundException;
import com.kagangunduz.vet.exception.RecordAlreadyExistException;
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
        try {
            genus.setName(genus.getName().toLowerCase());
            return genusRepository.save(genus);
        } catch (Exception exception) {
            throw new RecordAlreadyExistException("Cins adı zaten kayıtlı.");
        }

    }

    @Override
    public Genus findById(Long id) {
        return genusRepository.findById(id).orElseThrow(
                () -> new GenusNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
    }

    @Override
    public Genus update(Long id, Genus genus) {
        Optional<Genus> optionalGenus = genusRepository.findById(id);
        if (optionalGenus.isPresent()) {
            genus.setName(genus.getName().toLowerCase());
            Genus genusDb = optionalGenus.get();
            List<Genus> getAllByNameList = genusRepository.getAllByName(genus.getName());

            if (getAllByNameList.size() > 0 && !genus.getName().equals(genusDb.getName())) {
                throw new RecordAlreadyExistException("Cins adı zaten mevcut");
            } else {
                genusDb.setName(genus.getName());
                return genusRepository.save(genusDb);
            }

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
