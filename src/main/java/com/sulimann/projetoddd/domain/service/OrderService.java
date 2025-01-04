package com.sulimann.projetoddd.domain.service;

import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import com.sulimann.projetoddd.domain.entity.Customer;
import com.sulimann.projetoddd.domain.entity.Order;
import com.sulimann.projetoddd.domain.entity.OrderItem;

public class OrderService {

  public static BigDecimal getTotalOfAllOrders(Set<Order> orders) {
    return orders.stream().map(Order::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static Order placeOrder(Customer customer, Set<OrderItem> items) {
    notNull(customer, "Customer is required");
    var order = new Order(UUID.randomUUID().toString(), customer.getId(), items);
    customer.addRewardPoints(order.getTotal().divide(new BigDecimal("2")));
    return order;
  }

}
