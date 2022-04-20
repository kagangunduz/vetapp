package com.kagangunduz.vet.service;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OwnerService {

    //List<OwnerDto> findAll();
    List<Owner> findAll();

    //Page<OwnerDto> findAllWithPartOfFullName(String keyword, int pageNumber);
    Page<Owner> findAllWithPartOfFullName(String keyword, int pageNumber);

    //Page<OwnerDto> getAllPageable(int pageNumber);
    Page<Owner> getAllPageable(int pageNumber);

    //OwnerDto save(OwnerDto ownerDto);
    Owner save(Owner owner);

    //OwnerDto findById(Long id);
    Owner findById(Long id);

    //OwnerDto update(Long id, OwnerDto ownerDto);
    Owner update(Long id, Owner owner);

    //OwnerDto deleteById(Long id);
    Owner deleteById(Long id);

    Long getOwnerCount();

}
