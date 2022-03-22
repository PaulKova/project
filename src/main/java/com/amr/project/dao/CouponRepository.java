package com.amr.project.dao;

import com.amr.project.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
