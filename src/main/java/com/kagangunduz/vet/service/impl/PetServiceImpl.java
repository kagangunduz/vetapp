package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.exception.PetNotFoundException;
import com.kagangunduz.vet.exception.RecordAlreadyExistException;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet save(Pet pet) {
        try {
            return petRepository.save(pet);
        } catch (Exception exception) {
            throw new RecordAlreadyExistException("Bu isimde kayıt mevcut. Lütfen farklı bir isim girin.");
        }
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElseThrow(
                () -> new PetNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
    }

    @Override
    public Pet update(Long id, Pet pet) {
        pet.setName(pet.getName().toLowerCase());
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet petDb = optionalPet.get();
            petDb.setName(pet.getName());
            petDb.setBirthDate(pet.getBirthDate());
            petDb.setGenus(pet.getGenus());
            petDb.setSpecies(pet.getSpecies());
            petDb.setDescription(pet.getDescription());
            petDb.setOwner(pet.getOwner());
            try {
                return petRepository.save(pet);
            } catch (Exception exception) {
                throw new RecordAlreadyExistException("Bu isimde kayıt mevcut. Lütfen farklı bir isim girin.");
            }
        } else {
            throw new PetNotFoundException("Kayıt bulunamadı. Id: " + id);
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
    public Page<Pet> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return petRepository.findAll(pageable);
    }

    @Override
    public Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("name"));
        return petRepository.findAllWithPartOfNameOrOwnerFullName(keyword, pageable);
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

}
