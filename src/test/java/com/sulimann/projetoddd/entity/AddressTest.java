package com.sulimann.projetoddd.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AddressTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should create an Address")
    void shouldCreateAnAddress() {
      String street = "Rua das Flores";
      String number = "123";
      String city = "São Paulo";
      String state = "SP";
      String zipCode = "12345-678";

      Address address = new Address(street, number, city, state, zipCode);

      assertThat(address.getStreet()).isEqualTo(street);
      assertThat(address.getNumber()).isEqualTo(number);
      assertThat(address.getCity()).isEqualTo(city);
      assertThat(address.getState()).isEqualTo(state);
      assertThat(address.getZipCode()).isEqualTo(zipCode);
    }

    @Test
    @DisplayName("Should throw exception when street is empty")
    void shouldThrowExceptionWhenStreetIsEmpty() {
      assertThatThrownBy(() -> new Address("", "123", "São Paulo", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Street is required");
    }

    @Test
    @DisplayName("Should throw exception when number is empty")
    void shouldThrowExceptionWhenNumberIsEmpty() {
      assertThatThrownBy(() -> new Address("Rua das Flores", "", "São Paulo", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Number is required");
    }

    @Test
    @DisplayName("Should throw exception when city is empty")
    void shouldThrowExceptionWhenCityIsEmpty() {
      assertThatThrownBy(() -> new Address("Rua das Flores", "123", "", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("City is required");
    }

    @Test
    @DisplayName("Should throw exception when state is empty")
    void shouldThrowExceptionWhenStateIsEmpty() {
      assertThatThrownBy(() -> new Address("Rua das Flores", "123", "São Paulo", "", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("State is required");
    }

    @Test
    @DisplayName("Should throw exception when zipCode is empty")
    void shouldThrowExceptionWhenZipCodeIsEmpty() {
      assertThatThrownBy(() -> new Address("Rua das Flores", "123", "São Paulo", "SP", ""))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("ZipCode is required");
    }


  }
}
