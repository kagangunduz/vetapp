package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.dto.PetDto;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PetDto> findAll() {
        List<PetDto> petDtoList = Arrays.asList(modelMapper.map(petRepository.findAll(), PetDto[].class));
        return petDtoList;
    }

    @Override
    public PetDto save(PetDto petDto) {
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet = petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public PetDto findById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            return modelMapper.map(optionalPet.get(), PetDto.class);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public PetDto update(Long id, PetDto petDto) {
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet = petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
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
