package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("select o from Owner o where lower(o.fullName) like concat('%', :keyword, '%')")
    Page<Owner> findAllWithPartOfFullName(String keyword, Pageable pageable);

}
