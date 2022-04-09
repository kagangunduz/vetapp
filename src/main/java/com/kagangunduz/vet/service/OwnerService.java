package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.OwnerDto;
import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {

    Owner save(Owner owner);

    Owner findById(Long id);

    Owner updateById(Long id, Owner owner);

    Boolean deleteById(Long id);

    Page<Owner> getAllPageable(Pageable pageable);

    List<Owner> findAll();

}
