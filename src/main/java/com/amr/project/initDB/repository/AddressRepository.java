package com.amr.project.initDB.repository;

import com.amr.project.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressById(Long id);
}
