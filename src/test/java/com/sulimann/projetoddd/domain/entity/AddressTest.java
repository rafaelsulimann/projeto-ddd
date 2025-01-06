package com.sulimann.projetoddd.domain.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AddressTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should throw exception when street is empty")
    void shouldThrowExceptionWhenStreetIsEmpty() {
      assertThatThrownBy(() -> Address.create("", "123", "São Paulo", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Street is required");
    }

    @Test
    @DisplayName("Should throw exception when number is empty")
    void shouldThrowExceptionWhenNumberIsEmpty() {
      assertThatThrownBy(() -> Address.create("Rua das Flores", "", "São Paulo", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Number is required");
    }

    @Test
    @DisplayName("Should throw exception when city is empty")
    void shouldThrowExceptionWhenCityIsEmpty() {
      assertThatThrownBy(() -> Address.create("Rua das Flores", "123", "", "SP", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("City is required");
    }

    @Test
    @DisplayName("Should throw exception when state is empty")
    void shouldThrowExceptionWhenStateIsEmpty() {
      assertThatThrownBy(() -> Address.create("Rua das Flores", "123", "São Paulo", "", "12345-678"))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("State is required");
    }

    @Test
    @DisplayName("Should throw exception when zipCode is empty")
    void shouldThrowExceptionWhenZipCodeIsEmpty() {
      assertThatThrownBy(() -> Address.create("Rua das Flores", "123", "São Paulo", "SP", ""))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("ZipCode is required");
    }


  }
}
