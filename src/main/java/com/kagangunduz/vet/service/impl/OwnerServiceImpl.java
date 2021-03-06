package com.kagangunduz.vet.service.impl;

import com.kagangunduz.vet.entity.Owner;
import com.kagangunduz.vet.exception.OwnerNotFoundException;
import com.kagangunduz.vet.exception.RecordAlreadyExistException;
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

    @Override
    public Owner save(Owner owner) {
        try {
            owner.setFullName(owner.getFullName().toLowerCase());
            return ownerRepository.save(owner);
        } catch (Exception exception) {
            throw new RecordAlreadyExistException("Email adresi zaten kayıtlı.");
        }
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id)
        );
    }

    @Override
    public Owner update(Long id, Owner owner) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);

        if (optionalOwner.isPresent()) {
            Owner ownerDb = optionalOwner.get();
            List<Owner> getAllByEmailList = ownerRepository.getAllByEmail(owner.getEmail());

            if (getAllByEmailList.size() > 0 && !owner.getEmail().equals(ownerDb.getEmail())) {
                throw new RecordAlreadyExistException("Cins adı zaten mevcut");
            } else {
                ownerDb.setFullName(owner.getFullName().toLowerCase());
                ownerDb.setTelephoneNumber(owner.getTelephoneNumber());
                ownerDb.setEmail(owner.getEmail());
                ownerDb.setAddress(owner.getAddress());
                ownerDb.setPets(owner.getPets());
                return ownerRepository.save(owner);
            }
        } else {
            throw new EntityNotFoundException("Kayıt bulunamadı. Id: " + id);
        }
    }

    @Override
    public Owner deleteById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            Owner owner = optionalOwner.get();
            ownerRepository.deleteById(id);
            return owner;
        } else {
            throw new OwnerNotFoundException("Kayıt bulunumadı. Id: " + id);
        }
    }

    @Override
    public Page<Owner> getAllPageable(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("id").descending());
        return ownerRepository.findAll(pageable);
    }

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll(Sort.by("fullName"));
    }

    @Override
    public Page<Owner> findAllWithPartOfFullName(String keyword, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.by("fullName"));
        return ownerRepository.findAllWithPartOfFullName(keyword, pageable);
    }

    @Override
    public Long getOwnerCount() {
        return ownerRepository.count();
    }

}
