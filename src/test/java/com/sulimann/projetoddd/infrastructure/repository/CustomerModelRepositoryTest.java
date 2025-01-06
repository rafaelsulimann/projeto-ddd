package com.sulimann.projetoddd.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.sulimann.projetoddd.domain.entity.Address;
import com.sulimann.projetoddd.domain.entity.Customer;
import com.sulimann.projetoddd.infrastructure.model.CustomerModel;

@DataJpaTest
@Import(CustomerModelRepository.class)
public class CustomerModelRepositoryTest {

  @Autowired
  private CustomerModelJPARepository iCustomerModelRepository;

  @Autowired
  private CustomerModelRepository sut;

  @Autowired
  private TestEntityManager entityManager;

  @BeforeEach
  public void setUp() {
    iCustomerModelRepository.deleteAllInBatch();
  }

  @Nested
  class Create{

    @Test
    @DisplayName("Should persist a customer")
    void shouldPersistACustomer() {
      var customer = Customer.create(UUID.randomUUID().toString(), "name");
      var address = Address.create("street", "number", "city", "state", "zipCode");
      customer.setAddress(address);
      customer.activate();

      sut.create(customer);

      entityManager.flush();
      entityManager.clear();

      var persistedCustomer = iCustomerModelRepository.findById(UUID.fromString(customer.getId())).orElse(null);

      assertThat(persistedCustomer).isNotNull();
      assertThat(Customer.fromModel(persistedCustomer)).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    @DisplayName("Should throw exception when customer is null")
    void shouldThrowExceptionWhenCustomerIsNull() {
      assertThatThrownBy(() -> sut.create(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Customer is required");
    }
  }

  @Nested
  class Update{

    @Test
    @DisplayName("Should update a customer")
    void shouldUpdateACustomer() {
      var customer = Customer.create(UUID.randomUUID().toString(), "name");
      var address = Address.create("street", "number", "city", "state", "zipCode");
      customer.setAddress(address);
      customer.activate();
      iCustomerModelRepository.save(CustomerModel.fromEntity(customer));

      entityManager.flush();
      entityManager.clear();

      customer.changeName("new name");
      customer.setAddress(Address.create("new street", "new number", "new city", "new state", "new zipCode"));

      sut.update(customer);

      entityManager.flush();
      entityManager.clear();

      var persistedCustomer = iCustomerModelRepository.findById(UUID.fromString(customer.getId())).orElse(null);

      assertThat(persistedCustomer).isNotNull();
      assertThat(Customer.fromModel(persistedCustomer)).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    @DisplayName("Should throw exception when customer is null")
    void shouldThrowExceptionWhenCustomerIsNull() {
      assertThatThrownBy(() -> sut.update(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Customer is required");
    }
  }

  @Test
  @DisplayName("Should delete a customer")
  void shouldDeleteACustomer() {
    var customer = Customer.create(UUID.randomUUID().toString(), "name");
    var address = Address.create("street", "number", "city", "state", "zipCode");
    customer.setAddress(address);
    customer.activate();
    iCustomerModelRepository.save(CustomerModel.fromEntity(customer));

    entityManager.flush();
    entityManager.clear();

    sut.delete(customer);

    entityManager.flush();
    entityManager.clear();

    var persistedCustomer = iCustomerModelRepository.findById(UUID.fromString(customer.getId())).orElse(null);

    assertThat(persistedCustomer).isNull();
  }

  @Test
  @DisplayName("Should throw exception when customer is null")
  void shouldThrowExceptionWhenCustomerIsNull() {
    assertThatThrownBy(() -> sut.delete(null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Customer is required");
  }

  @Nested
  class FindById{

    @Test
    @DisplayName("Should return a customer by id")
    void shouldReturnACustomerById() {
      var customer = Customer.create(UUID.randomUUID().toString(), "name");
      var address = Address.create("street", "number", "city", "state", "zipCode");
      customer.setAddress(address);
      customer.activate();
      iCustomerModelRepository.save(CustomerModel.fromEntity(customer));

      entityManager.flush();
      entityManager.clear();

      var persistedCustomer = iCustomerModelRepository.findById(UUID.fromString(customer.getId())).orElse(null);

      assertThat(persistedCustomer).isNotNull();
      assertThat(Customer.fromModel(persistedCustomer)).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    @DisplayName("Should throw exception when customer id is null")
    void shouldThrowExceptionWhenCustomerIdIsNull() {
      assertThatThrownBy(() -> sut.findById(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Customer id is required");
    }
  }

}
