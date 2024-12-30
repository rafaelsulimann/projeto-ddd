package com.sulimann.projetoddd.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.sulimann.projetoddd.entity.Order;
import com.sulimann.projetoddd.entity.OrderItem;

public class OrderServiceTest {

  @Nested
  class GetTotalOfAllOrders {

    @Test
    @DisplayName("Should return the total of all orders")
    void shouldReturnTheTotalOfAllOrders() {
      var item1 = new OrderItem("1", "321", "item 1", new BigDecimal("100.00"), 1);
      var item2 = new OrderItem("2", "321", "item 2", new BigDecimal("200.00"), 2);

      var order1 = new Order("1", "123", Set.of(item1));
      var order2 = new Order("2", "123", Set.of(item2));

      var total = OrderService.getTotalOfAllOrders(Set.of(order1, order2));

      assertThat(total).isEqualTo(new BigDecimal("500.00"));
    }
  }

}
