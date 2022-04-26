package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OwnerService {

    Owner save(Owner owner);

    Owner findById(Long id);

    Owner update(Long id, Owner owner);

    Owner deleteById(Long id);

    Page<Owner> getAllPageable(int pageNumber);

    List<Owner> findAll();

    Page<Owner> findAllWithPartOfFullName(String keyword, int pageNumber);

    Long getOwnerCount();

}
