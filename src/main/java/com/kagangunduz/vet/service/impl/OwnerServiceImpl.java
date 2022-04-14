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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Owner> findAllWithPartOfFullName(String fullName) {
        if (fullName.isEmpty()) {
            return new ArrayList<Owner>();
        }
        return ownerRepository.findAllWithPartOfFullName(fullName);
    }

    @Override
    public Page<Owner> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, Sort.by("id").descending());


        return ownerRepository.findAll(pageable);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new OwnerNotFoundException("Kayıt bulunumadı. Hayvan Sahibi Id: " + id)
        );
    }

    @Override
    public Owner update(Long id, Owner owner) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            Owner ownerDb = optionalOwner.get();
            ownerDb.setFullName(owner.getFullName());
            ownerDb.setTelephoneNumber(owner.getTelephoneNumber());
            ownerDb.setEmail(owner.getEmail());
            ownerDb.setAddress(owner.getAddress());
            return ownerRepository.save(ownerDb);
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. id: " + id);
        }
    }

    @Override
    public Owner deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            ownerRepository.deleteById(id);
            return optionalOwner.get();
        } else {
            throw new OwnerNotFoundException("Kayıt bulunumadı. Hayvan Sahibi Id: " + id);
        }
    }

    @Override
    public Long getOwnerCount() {
        return ownerRepository.count();
    }


}
