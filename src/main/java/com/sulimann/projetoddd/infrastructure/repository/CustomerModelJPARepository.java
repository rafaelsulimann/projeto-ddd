package com.sulimann.projetoddd.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sulimann.projetoddd.infrastructure.model.CustomerModel;

public interface CustomerModelJPARepository extends JpaRepository<CustomerModel, UUID>{

}
