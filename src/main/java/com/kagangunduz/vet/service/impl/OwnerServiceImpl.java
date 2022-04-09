package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.repository.OwnerRepository;
import com.kagangunduz.vet.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            return owner.get();
        } else {
            throw new OwnerNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Owner updateById(Long id, Owner owner) {
        return ownerRepository.save(owner);
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
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Page<Owner> getAllPageable(Pageable pageable) {
        return ownerRepository.findAll(pageable);
        
    }
}
