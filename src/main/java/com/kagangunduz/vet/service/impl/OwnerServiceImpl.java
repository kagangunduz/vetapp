package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.dto.OwnerDto;
import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.repository.OwnerRepository;
import com.kagangunduz.vet.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Override
    public OwnerDto save(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        owner = ownerRepository.save(owner);
        ownerDto = modelMapper.map(owner, OwnerDto.class);
        return ownerDto;
    }

    @Override
    public OwnerDto findById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            return modelMapper.map(owner.get(), OwnerDto.class);
        } else {
            throw new OwnerNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public OwnerDto updateById(Long id, OwnerDto ownerDto) {
        Owner ownerDb = ownerRepository.getById(id);
        ownerDb.setFullName(ownerDb.getFullName());
        ownerDb.setTelephoneNumber(ownerDb.getTelephoneNumber());
        ownerDb.setEmail(ownerDb.getEmail());
        ownerRepository.save(ownerDb);
        return modelMapper.map(ownerDb, OwnerDto.class);
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            ownerRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            throw new OwnerNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Page<OwnerDto> getAllPageable(Pageable pageable) {
        Page<Owner> owners = ownerRepository.findAll(pageable);
        OwnerDto[] ownerDtos = modelMapper.map(owners.getContent(), OwnerDto[].class);
        List<OwnerDto> ownerDtoList = Arrays.asList(ownerDtos);
        return new PageImpl<>(ownerDtoList, pageable, owners.getTotalElements());
    }

    @Override
    public List<OwnerDto> findAll() {
        List<Owner> ownerList = ownerRepository.findAll();
        List<OwnerDto> ownerDtoList = Arrays.asList(modelMapper.map(ownerList, OwnerDto[].class));
        return ownerDtoList;
    }
}
