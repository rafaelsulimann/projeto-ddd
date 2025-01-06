package com.sulimann.projetoddd.infrastructure.repository;

import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sulimann.projetoddd.domain.entity.Customer;
import com.sulimann.projetoddd.domain.repository.ICustomerRepository;
import com.sulimann.projetoddd.infrastructure.model.CustomerModel;

@Repository
public class CustomerModelRepository implements ICustomerRepository{

  private final CustomerModelJPARepository customerModelRepository;

  public CustomerModelRepository(CustomerModelJPARepository customerModelRepository) {
    this.customerModelRepository = customerModelRepository;
  }

  @Override
  public void create(Customer entity) {
    notNull(entity, "Customer is required");
    this.customerModelRepository.save(CustomerModel.fromEntity(entity));
  }

  @Override
  public void update(Customer entity) {
    notNull(entity, "Customer is required");
    this.customerModelRepository.save(CustomerModel.fromEntity(entity));
  }

  @Override
  public void delete(Customer entity) {
    notNull(entity, "Customer is required");
    this.customerModelRepository.delete(CustomerModel.fromEntity(entity));
  }

  @Override
  public Optional<Customer> findById(String id) {
    notNull(id, "Customer id is required");
    return this.customerModelRepository.findById(UUID.fromString(id)).map(Customer::fromModel);
  }

}
