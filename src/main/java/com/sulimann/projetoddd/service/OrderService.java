package com.sulimann.projetoddd.service;

import java.math.BigDecimal;
import java.util.Set;

import com.sulimann.projetoddd.entity.Order;

public class OrderService {

  public static BigDecimal getTotalOfAllOrders(Set<Order> orders) {
    return orders.stream().map(Order::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
