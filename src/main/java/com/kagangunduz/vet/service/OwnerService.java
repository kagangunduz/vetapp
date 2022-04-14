package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OwnerService {

    List<Owner> findAll();

    List<Owner> findAllWithPartOfFullName(String fullName);

    Page<Owner> getAllPageable(int pageNumber);

    Owner save(Owner owner);

    Owner findById(Long id);

    Owner update(Long id, Owner owner);

    Owner deleteById(Long id);

    Long getOwnerCount();


}
