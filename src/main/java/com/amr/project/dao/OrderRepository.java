package com.amr.project.dao;

import com.amr.project.model.entity.Category;
import com.amr.project.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
