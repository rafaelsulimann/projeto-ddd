package com.sulimann.projetoddd.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isTrue;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

public class OrderItem {

  private String id;
  private String name;
  private BigDecimal price;
  private int quantity;

  public OrderItem(String id, String name, BigDecimal price, int quantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.validate();
  }

  private void validate() {
    hasText(this.id, "Id is required");
    hasText(this.name, "Name is required");
    notNull(this.price, "Price is required");
    isTrue(this.quantity > 0, "Quantity must be greater than 0");
  }

  public BigDecimal getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

}
