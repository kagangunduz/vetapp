package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.OwnerDto;

import java.util.List;

public interface OwnerService {

    List<OwnerDto> findAll();

    OwnerDto save(OwnerDto ownerDto);

    OwnerDto findById(Long id);

    OwnerDto update(Long id, OwnerDto ownerDto);

    Boolean deleteById(Long id);

    /*Page<Owner> getAllPageable(Pageable pageable);*/

}
