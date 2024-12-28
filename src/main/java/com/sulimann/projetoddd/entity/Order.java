package com.sulimann.projetoddd.entity;

import java.math.BigDecimal;
import java.util.Set;

public class Order {

  private String id;
  private String customerId;
  private Set<OrderItem> items;
  private BigDecimal total;

  public Order(String id, String customerId, Set<OrderItem> items) {
    this.id = id;
    this.customerId = customerId;
    this.items = items;
    this.total = this.getTotal();
  }

  private BigDecimal getTotal() {
    return this.items.stream()
      .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
