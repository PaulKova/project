package com.amr.project.dao;


import com.amr.project.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ShopRepository extends JpaRepository<Shop, Long> {

}
