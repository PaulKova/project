package com.amr.project.dao;

import com.amr.project.model.entity.Order1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order1, Long> {
}
