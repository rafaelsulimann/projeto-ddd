package com.sulimann.projetoddd.service;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import com.sulimann.projetoddd.entity.Customer;
import com.sulimann.projetoddd.entity.Order;
import com.sulimann.projetoddd.entity.OrderItem;

public class OrderService {

  public static BigDecimal getTotalOfAllOrders(Set<Order> orders) {
    return orders.stream().map(Order::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public static Order placeOrder(Customer customer, Set<OrderItem> items) {
    var order = new Order(UUID.randomUUID().toString(), customer.getId(), items);
    customer.addRewardPoints(order.getTotal().divide(new BigDecimal("2")));
    return order;
  }

}
