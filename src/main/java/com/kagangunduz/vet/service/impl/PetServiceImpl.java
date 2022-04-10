package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            return pet.get();
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Pet update(Long id, Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }


    /*
    @Override
    public Page<Pet> getAllPageable(Pageable pageable) {
        return petRepository.findAll(pageable);
    }*/


}
