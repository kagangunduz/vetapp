package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.exception.PetNotFoundException;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

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
            throw new PetNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Pet updateById(Long id, Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Page<Pet> getAllPageable(Pageable pageable) {
        return petRepository.findAll(pageable);
    }


}
