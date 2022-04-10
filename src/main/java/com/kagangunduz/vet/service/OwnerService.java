package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Owner;

import java.util.List;

public interface OwnerService {

    List<Owner> findAll();

    Owner save(Owner owner);

    Owner findById(Long id);

    Owner update(Long id, Owner owner);

    Boolean deleteById(Long id);

    /*Page<Owner> getAllPageable(Pageable pageable);*/

}
