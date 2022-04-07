package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.dto.PetDto;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    @Override
    public PetDto save(PetDto petDto) {
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet = petRepository.save(pet);
        petDto = modelMapper.map(pet, PetDto.class);
        return petDto;
    }

    @Override
    public PetDto getById(Long id) {
        Pet pet = petRepository.getById(id);
        return modelMapper.map(pet, PetDto.class);
    }

    @Override
    public PetDto updateById(Long id, PetDto petDto) {
        Pet petDb = petRepository.getById(id);
        petDb.setName(petDto.getName());
        petDb.setAge(petDto.getAge());
        petDb.setGenus(petDto.getGenus());
        petDb.setDescription(petDto.getDescription());
        petRepository.save(petDb);
        return modelMapper.map(petDb, PetDto.class);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    public Page<PetDto> getAllPageable(Pageable pageable) {
        Page<Pet> pets = petRepository.findAll(pageable);
        PetDto[] petDtos = modelMapper.map(pets.getContent(), PetDto[].class);
        List<PetDto> petDtoList = Arrays.asList(petDtos);
        return new PageImpl<>(petDtoList, pageable, pets.getTotalElements());
    }


}
