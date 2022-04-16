package com.kagangunduz.vet.service.impl;

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
import java.util.List;
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
                () -> new PetNotFoundException("Kayıt bulunumadı. Hayvan Sahibi Id: " + id)
        );
    }

    @Override
    public Pet update(Long id, Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet petDb = optionalPet.get();
            petDb.setName(pet.getName());
            petDb.setAge(pet.getAge());
            petDb.setGenus(pet.getGenus());
            petDb.setDescription(pet.getDescription());
            petDb.setOwner(pet.getOwner());
            return petRepository.save(petDb);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Pet deleteById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            petRepository.deleteById(id);
            return optionalPet.get();
        } else {
            throw new PetNotFoundException("Kayıt bulunumadı. Hayvan Id: " + id);
        }
    }

    @Override
    public Long getPetCount() {
        return petRepository.count();
    }

}
