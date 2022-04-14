package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("select o from Owner o where lower(o.fullName) like concat('%', :fullName, '%')")
    List<Owner> findAllWithPartOfFullName(String fullName);

    /*Alternatif*/
    /*List<Owner> findAllByFullNameContainsIgnoreCase(String fullName);*/


}
