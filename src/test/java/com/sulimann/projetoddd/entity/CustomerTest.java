package com.sulimann.projetoddd.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CustomerTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should throw exception when id is empty")
    void shouldThrowExceptionWhenIdIsEmpty() {
      assertThatThrownBy(() -> new Customer("", "Rafael"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
      assertThatThrownBy(() -> new Customer("123", ""))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }
  }

  @Nested
  class ChangeName {

    @Test
    @DisplayName("Should change name")
    void shouldChangeName(){
      var customer = new Customer("123", "Rafael");
      customer.changeName("Rafael Suliman");

      assertThat(customer.getName()).isEqualTo("Rafael Suliman");
    }

    @Test
    @DisplayName("Should throw exception when name is empty on Customer.changeName")
    void shouldThrowExceptionWhenNameIsEmptyOnChangeName() {
      var customer = new Customer("123", "Rafael");
      assertThatThrownBy(() -> customer.changeName(""))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }

  }

  @Nested
  class Activate {

    @Test
    @DisplayName("Should activate customer")
    void shouldActivateCustomer() {
      var customer = new Customer("123", "Rafael");
      customer.setAddress(new Address("Rua 1", "123", "12345-123", "São Paulo", "SP"));
      customer.activate();

      assertThat(customer.isActive()).isTrue();
    }

    @Test
    @DisplayName("Should throw exception when activate a customer without address")
    void shouldThrowExceptionWhenActivateCustomerWithoutAddress() {
      var customer = new Customer("123", "Rafael");

      assertThatThrownBy(() -> customer.activate())
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Address is mandatory to activate the customer");
    }

  }

  @Nested
  class SetAddress {

    @Test
    @DisplayName("Should set address")
    void shouldSetAddress() {
      var customer = new Customer("123", "Rafael");
      var address = new Address("Rua 1", "123", "12345-123", "São Paulo", "SP");
      customer.setAddress(address);

      assertThat(customer.getAddress()).isEqualTo(address);
    }

    @Test
    @DisplayName("Should throw exception when set address with null")
    void shouldThrowExceptionWhenSetAddressWithNull() {
      var customer = new Customer("123", "Rafael");

      assertThatThrownBy(() -> customer.setAddress(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Address is required");
    }
  }

  @Nested
  class Deactivate {

    @Test
    @DisplayName("Should deactivate customer")
    void shouldDeactivateCustomer() {
      var customer = new Customer("123", "Rafael");
      customer.deactivate();

      assertThat(customer.isActive()).isFalse();
    }
  }

}
