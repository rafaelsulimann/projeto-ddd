package com.sulimann.projetoddd.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.sulimann.projetoddd.domain.entity.Customer;
import com.sulimann.projetoddd.domain.entity.Order;
import com.sulimann.projetoddd.domain.entity.OrderItem;

public class OrderServiceTest {

  @Nested
  class GetTotalOfAllOrders {

    @Test
    @DisplayName("Should return the total of all orders")
    void shouldReturnTheTotalOfAllOrders() {
      var item1 = OrderItem.create("1", "321", "item 1", new BigDecimal("100.00"), 1);
      var item2 = OrderItem.create("2", "321", "item 2", new BigDecimal("200.00"), 2);

      var order1 = Order.create("1", "123", Set.of(item1));
      var order2 = Order.create("2", "123", Set.of(item2));

      var total = OrderService.getTotalOfAllOrders(Set.of(order1, order2));

      assertThat(total).isEqualTo(new BigDecimal("500.00"));
    }
  }

  @Nested
  class PlaceOrder {

    @Test
    @DisplayName("Should place an order")
    void shouldPlaceAnOrder() {
      var customer = Customer.create("123", "Rafael");
      var item1 = OrderItem.create("1", "321", "item 1", new BigDecimal("100.00"), 1);
      var item2 = OrderItem.create("2", "321", "item 2", new BigDecimal("200.00"), 2);

      var order = OrderService.placeOrder(customer, Set.of(item1, item2));

      assertThat(customer.getRewardPoints()).isEqualTo(new BigDecimal("250.00"));
      assertThat(order.getTotal()).isEqualTo(new BigDecimal("500.00"));
    }

    @Test
    @DisplayName("Should throw exception when customer is null")
    void shouldThrowExceptionWhenCustomerIsNull() {
      var item1 = OrderItem.create("1", "321", "item 1", new BigDecimal("100.00"), 1);
      var item2 = OrderItem.create("2", "321", "item 2", new BigDecimal("200.00"), 2);

      assertThatThrownBy(() -> OrderService.placeOrder(null, Set.of(item1, item2))
      ).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Customer is required");
    }

    @Test
    @DisplayName("Should throw exception when items are empty")
    void shouldThrowExceptionWhenItemsAreEmpty() {
      var customer = Customer.create("123", "Rafael");

      assertThatThrownBy(() -> OrderService.placeOrder(customer, Set.of())
      ).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Items are required");
    }
  }

}
