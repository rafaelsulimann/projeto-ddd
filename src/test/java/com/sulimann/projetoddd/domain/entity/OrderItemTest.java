package com.sulimann.projetoddd.domain.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderItemTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should throw exception when id is empty")
    void shouldThrowExceptionWhenIdIsEmpty() {
      assertThatThrownBy(() -> new OrderItem("", "321", "item 1", new BigDecimal("100.00"), 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }

    @Test
    @DisplayName("Should throw exception when customerId is empty")
    void shouldThrowExceptionWhenCustomerIdIsEmpty() {
      assertThatThrownBy(() -> new OrderItem("123", "", "item 1", new BigDecimal("100.00"), 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("ProductId is required");
    }
    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
      assertThatThrownBy(() -> new OrderItem("123", "321", "", new BigDecimal("100.00"), 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void shouldThrowExceptionWhenPriceIsNull() {
      assertThatThrownBy(() -> new OrderItem("123", "321", "item 1", null, 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price is required");
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when price is equal or less than 0")
    @ValueSource(strings = {"0", "-1"})
    void shouldThrowExceptionWhenPriceIsLessThan1(String price) {
      assertThatThrownBy(() -> new OrderItem("123", "321", "item 1", new BigDecimal(price), 1))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("Price must be greater than 0");
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when quantity is less than 1")
    @ValueSource(ints = {0, -1})
    void shouldThrowExceptionWhenQuantityIsLessThan1(int quantity) {
      assertThatThrownBy(() -> new OrderItem("123", "321", "item 1", new BigDecimal("100.00"), quantity))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Quantity must be greater than 0");
    }
  }
}
