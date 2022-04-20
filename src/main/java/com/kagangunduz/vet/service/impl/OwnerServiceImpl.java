package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.repository.OwnerRepository;
import com.kagangunduz.vet.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    //private final ModelMapper modelMapper;

    /*@Override
    public List<OwnerDto> findAll() {
        OwnerDto[] ownerDtos = modelMapper.map(ownerRepository.findAll(Sort.by("fullName")), OwnerDto[].class);
        return Arrays.asList(ownerDtos);
    }*/
    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll(Sort.by("fullName"));
    }

    /*@Override
    public Page<OwnerDto> findAllWithPartOfFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("fullName"));
        Page<Owner> ownerPage = ownerRepository.findAllWithPartOfFullName(keyword, pageable);
        List<OwnerDto> ownerDtos = Arrays.asList(modelMapper.map(ownerPage.getContent(), OwnerDto[].class));
        return new PageImpl<OwnerDto>(ownerDtos, pageable, ownerPage.getTotalElements());
    }*/
    @Override
    public Page<Owner> findAllWithPartOfFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("fullName"));
        return ownerRepository.findAllWithPartOfFullName(keyword, pageable);
    }

    /*@Override
    public Page<OwnerDto> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        Page<Owner> ownerPage = ownerRepository.findAll(pageable);
        List<OwnerDto> ownerDtos = Arrays.asList(modelMapper.map(ownerPage.getContent(), OwnerDto[].class));
        return new PageImpl<OwnerDto>(ownerDtos, pageable, ownerPage.getTotalElements());
    }*/
    @Override
    public Page<Owner> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return ownerRepository.findAll(pageable);
    }

    /*@Override
    public OwnerDto save(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        owner = ownerRepository.save(owner);
        return modelMapper.map(owner, OwnerDto.class);
    }*/
    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    /*@Override
    public OwnerDto findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
        return modelMapper.map(owner, OwnerDto.class);
    }*/
    @Override
    public Owner findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
        return owner;
    }

    /*@Override
    public OwnerDto update(Long id, OwnerDto ownerDto) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();
            owner.setFullName(ownerDto.getFullName());
            owner.setTelephoneNumber(ownerDto.getTelephoneNumber());
            owner.setEmail(ownerDto.getEmail());
            owner.setAddress(ownerDto.getAddress());
            owner = ownerRepository.save(owner);
            return modelMapper.map(owner, OwnerDto.class);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }*/
    @Override
    public Owner update(Long id, Owner owner) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            Owner ownerDb = optionalOwner.get();
            ownerDb.setFullName(owner.getFullName());
            ownerDb.setTelephoneNumber(owner.getTelephoneNumber());
            ownerDb.setEmail(owner.getEmail());
            ownerDb.setAddress(owner.getAddress());
            return ownerRepository.save(owner);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    /*@Override
    public OwnerDto deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            OwnerDto ownerDto = modelMapper.map(optionalOwner.get(), OwnerDto.class);
            ownerRepository.deleteById(id);
            return ownerDto;
        } else {
            throw new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }*/
    @Override
    public Owner deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            ownerRepository.deleteById(id);
            return optionalOwner.get();
        } else {
            throw new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }

    @Override
    public Long getOwnerCount() {
        return ownerRepository.count();
    }
    
}
