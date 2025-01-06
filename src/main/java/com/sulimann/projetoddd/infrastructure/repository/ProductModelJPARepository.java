package com.sulimann.projetoddd.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.projetoddd.infrastructure.model.ProductModel;

public interface ProductModelJPARepository extends JpaRepository<ProductModel, UUID>{

}
