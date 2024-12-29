package com.sulimann.projetoddd.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrderItemTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should throw exception when id is empty")
    void shouldThrowExceptionWhenIdIsEmpty() {
      assertThatThrownBy(() -> new OrderItem("", "item 1", new BigDecimal("100.00"), 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
      assertThatThrownBy(() -> new OrderItem("123", "", new BigDecimal("100.00"), 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void shouldThrowExceptionWhenPriceIsNull() {
      assertThatThrownBy(() -> new OrderItem("123", "item 1", null, 1))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price is required");
    }

    @Test
    @DisplayName("Should throw exception when quantity is less than 1")
    void shouldThrowExceptionWhenQuantityIsLessThan1() {
      assertThatThrownBy(() -> new OrderItem("123", "item 1", new BigDecimal("100.00"), 0))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Quantity must be greater than 0");
    }
  }
}
