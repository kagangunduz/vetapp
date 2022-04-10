package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.dto.OwnerDto;
import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.repository.OwnerRepository;
import com.kagangunduz.vet.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Override

    public List<OwnerDto> findAll() {
        List<OwnerDto> ownerDtoList = Arrays.asList(modelMapper.map(ownerRepository.findAll(), OwnerDto[].class));
        return ownerDtoList;
    }

    @Override
    public OwnerDto save(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        owner = ownerRepository.save(owner);
        ownerDto = modelMapper.map(owner, OwnerDto.class);
        return ownerDto;
    }

    @Override
    public OwnerDto findById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            OwnerDto ownerDto = modelMapper.map(optionalOwner.get(), OwnerDto.class);
            return ownerDto;
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public OwnerDto update(Long id, OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        owner = ownerRepository.save(owner);
        return modelMapper.map(owner, OwnerDto.class);
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            ownerRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    /*

    @Override
    public Page<Owner> getAllPageable(Pageable pageable) {
        return ownerRepository.findAll(pageable);
        
    }*/
}
