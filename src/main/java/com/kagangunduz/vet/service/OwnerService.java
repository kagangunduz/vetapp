package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.OwnerDto;
import com.kagangunduz.vet.dto.PetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OwnerService {

    OwnerDto save(OwnerDto ownerDto);

    OwnerDto findById(Long id);

    Page<OwnerDto> getAllPageable(Pageable pageable);

}
