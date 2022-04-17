package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.exception.PetNotFoundException;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return petRepository.findAllWithPartOfNameOrOwnerFullName(keyword, pageable);
    }

    @Override
    public Page<Pet> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return petRepository.findAll(pageable);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new PetNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
    }

    @Override
    public Pet update(Long id, Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet petDb = optionalPet.get();
            petDb.setName(pet.getName());
            petDb.setBirthDate(pet.getBirthDate());
            petDb.setGenus(pet.getGenus());
            petDb.setDescription(pet.getDescription());
            petDb.setOwner(pet.getOwner());
            return petRepository.save(petDb);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    @Override
    public Pet deleteById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            petRepository.deleteById(id);
            return optionalPet.get();
        } else {
            throw new PetNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }

    @Override
    public Long getPetCount() {
        return petRepository.count();
    }

    @Override
    public String getAgeInfo(Long id) {
        Pet pet = this.findById(id);
        if (pet.getBirthDate() != null) {
            LocalDate birthDate = Instant.ofEpochMilli(pet.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            Period lifePeriod = Period.between(birthDate, now);
            return lifePeriod.getYears() + " yıl, " + lifePeriod.getMonths() + " ay, " + lifePeriod.getDays() + " gün";
        }
        return "";
    }

    @Override
    public Map<Genus, String> getGenusAsHashMap() {
        Map<Genus, String> genusHashMap = new HashMap<>();
        for (Genus genus : Genus.values()) {
            genusHashMap.put(genus, genus.getValue());
        }
        return genusHashMap;
    }

}
