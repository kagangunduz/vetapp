package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Genus;
import com.kagangunduz.vet.entity.Pet;
import com.kagangunduz.vet.exception.PetNotFoundException;
import com.kagangunduz.vet.repository.PetRepository;
import com.kagangunduz.vet.service.PetService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;
    //private final ModelMapper modelMapper;

    /*@Override
    public PetDto save(PetDto petDto) {
        Pet pet = modelMapper.map(petDto, Pet.class);
        pet = petRepository.save(pet);
        return modelMapper.map(pet, PetDto.class);
    }*/
    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    /*@Override
    public PetDto findById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(
                () -> new PetNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
        return modelMapper.map(pet, PetDto.class);
    }*/
    @Override
    public Pet findById(Long id) {
        Pet pet = petRepository.findById(id).orElseThrow(
                () -> new PetNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
        return pet;
    }

    /*@Override
    public PetDto update(Long id, PetDto petDto) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            pet.setName(petDto.getName());
            pet.setBirthDate(petDto.getBirthDate());
            pet.setGenus(petDto.getGenus());
            pet.setSpecies(petDto.getSpecies());
            pet.setDescription(petDto.getDescription());
            Owner owner = modelMapper.map(petDto.getOwner(), Owner.class);
            pet.setOwner(owner);
            pet = petRepository.save(pet);
            return modelMapper.map(pet, PetDto.class);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }*/
    @Override
    public Pet update(Long id, Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            Pet petDb = optionalPet.get();
            petDb.setName(pet.getName());
            petDb.setBirthDate(pet.getBirthDate());
            petDb.setGenus(pet.getGenus());
            petDb.setSpecies(pet.getSpecies());
            petDb.setDescription(pet.getDescription());
            petDb.setOwner(pet.getOwner());
            return petRepository.save(pet);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    /*@Override
    public PetDto deleteById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            petRepository.deleteById(id);
            return modelMapper.map(optionalPet.get(), PetDto.class);
        } else {
            throw new PetNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }*/
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

    /*@Override
    public Page<PetDto> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        Page<Pet> petPage = petRepository.findAll(pageable);
        List<PetDto> petDtos = Arrays.asList(modelMapper.map(petPage.getContent(), PetDto[].class));
        return new PageImpl<PetDto>(petDtos, pageable, petPage.getTotalElements());
    }*/
    @Override
    public Page<Pet> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return petRepository.findAll(pageable);
    }

    /*@Override
    public Page<PetDto> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("name"));
        Page<Pet> petPage = petRepository.findAllWithPartOfNameOrOwnerFullName(keyword, pageable);
        List<PetDto> petDtos = Arrays.asList(modelMapper.map(petPage.getContent(), PetDto[].class));
        return new PageImpl<PetDto>(petDtos, pageable, petPage.getTotalElements());
    }*/
    @Override
    public Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("name"));
        return petRepository.findAllWithPartOfNameOrOwnerFullName(keyword, pageable);
    }

    /*@Override
    public String getAgeInfo(Long id) {
        PetDto petDto = this.findById(id);
        if (petDto.getBirthDate() != null) {
            LocalDate birthDate = Instant.ofEpochMilli(petDto.getBirthDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate now = LocalDate.now();
            Period lifePeriod = Period.between(birthDate, now);
            return lifePeriod.getYears() + " yıl, " + lifePeriod.getMonths() + " ay, " + lifePeriod.getDays() + " gün";
        }
        return "";
    }*/
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
    public Long getPetCount() {
        return petRepository.count();
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
