package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.PetDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PetService {

    PetDto save(PetDto petDto);

    PetDto getById(Long id);

    Page<PetDto> getAllPageable(Pageable pageable);

}
