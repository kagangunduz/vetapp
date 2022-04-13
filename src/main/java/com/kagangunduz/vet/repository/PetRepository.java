package com.kagangunduz.vet.repository;

import com.kagangunduz.vet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p from Pet p left join Owner o on p.owner.id = o.id where " +
            "lower(p.name) like concat('%',:name,'%') or lower(o.fullName) like concat('%', :name, '%')")
    List<Pet> findAllWithPartOfNameOrOwnerFullName(String name);

}
