package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isFalse;

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
    this.total = this.calculateTotal();
    this.validate();
  }

  private void validate() {
    hasText(this.id, "Id is required");
    hasText(this.customerId, "CustomerId is required");
    isFalse(this.items.isEmpty(), "Items are required");
  }

  private BigDecimal calculateTotal() {
    return this.items.stream()
        .map(item -> item.getSubtotal())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getTotal() {
    return total;
  }

}
