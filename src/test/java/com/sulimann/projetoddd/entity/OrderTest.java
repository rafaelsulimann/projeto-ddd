package com.sulimann.projetoddd.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrderTest {

  @Nested
  class Constructor {
    @Test
    @DisplayName("Should throw exception when id is empty")
    void shouldThrowExceptionWhenIdIsEmpty() {
      var item1 = new OrderItem("123", "item 1", new BigDecimal("100.00"), 1);
      var item2 = new OrderItem("456", "item 2", new BigDecimal("100.00"), 2);

      assertThatThrownBy(() -> new Order("", "123", Set.of(item1, item2)))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }

    @Test
    @DisplayName("Should throw exception when customerId is empty")
    void shouldThrowExceptionWhenCustomerIdIsEmpty() {
      var item1 = new OrderItem("123", "item 1", new BigDecimal("100.00"), 1);
      var item2 = new OrderItem("456", "item 2", new BigDecimal("100.00"), 2);

      assertThatThrownBy(() -> new Order("123", "", Set.of(item1, item2)))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("CustomerId is required");
    }

    @Test
    @DisplayName("Should throw exception when items is empty")
    void shouldThrowExceptionWhenItemsIsEmpty() {
      assertThatThrownBy(() -> new Order("123", "123", Set.of()))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Items are required");
    }

    @Test
    @DisplayName("Should calculate total")
    void shouldCalculateTotal() {
      var item1 = new OrderItem("123", "item 1", new BigDecimal("100.00"), 1);
      var item2 = new OrderItem("456", "item 2", new BigDecimal("100.00"), 2);
      var order = new Order("123", "123", Set.of(item1));
      var order2 = new Order("123", "123", Set.of(item1, item2));

      assertThat(order.getTotal()).isEqualTo(new BigDecimal("100.00"));
      assertThat(order2.getTotal()).isEqualTo(new BigDecimal("300.00"));
    }
  }


}
