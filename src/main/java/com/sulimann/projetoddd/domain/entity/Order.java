package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isFalse;

import java.math.BigDecimal;
import java.util.Set;

import com.sulimann.projetoddd.domain.entity.contracts.OrderContract;

import lombok.Getter;

@Getter
public class Order {

  private String id;
  private String customerId;
  private Set<OrderItem> items;
  private BigDecimal total;

  private Order(String id, String customerId, Set<OrderItem> items, BigDecimal total) {
    this.id = id;
    this.customerId = customerId;
    this.items = items;
    this.total = total == null ? this.calculateTotal() : total;
    this.validate();
  }

  public static Order create(String id, String customerId, Set<OrderItem> items) {
    return new Order(id, customerId, items, null);
  }

  @SuppressWarnings("unchecked")
  public static Order fromModel(OrderContract model) {
    return new Order(model.getId(), model.getCustomerId(), (Set<OrderItem>) model.getItems(), model.getTotal());
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

}
