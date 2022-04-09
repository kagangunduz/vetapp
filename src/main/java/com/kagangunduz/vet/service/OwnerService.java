package com.kagangunduz.vet.service;

import com.kagangunduz.vet.dto.OwnerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OwnerService {

    OwnerDto save(OwnerDto ownerDto);

    OwnerDto findById(Long id);

    OwnerDto updateById(Long id, OwnerDto ownerDto);

    Boolean deleteById(Long id);

    Page<OwnerDto> getAllPageable(Pageable pageable);

    List<OwnerDto> findAll();

}
