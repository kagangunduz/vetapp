package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p from Pet p left join Owner o on p.owner.id = o.id where " +
            "lower(p.name) like concat('%',:keyword,'%') or lower(o.fullName) like concat('%', :keyword, '%')")
    Page<Pet> findAllWithPartOfNameOrOwnerFullName(String keyword, Pageable pageable);

}
