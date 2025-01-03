package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isTrue;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

public class OrderItem {

  private String id;
  private String productId;
  private String name;
  private BigDecimal price;
  private int quantity;

  public OrderItem(String id, String productId, String name, BigDecimal price, int quantity) {
    this.id = id;
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.validate();
  }

  private void validate() {
    hasText(this.id, "Id is required");
    hasText(this.productId, "ProductId is required");
    hasText(this.name, "Name is required");
    notNull(this.price, "Price is required");
    isTrue(this.price.compareTo(BigDecimal.ZERO) > 0, "Price must be greater than 0");
    isTrue(this.quantity > 0, "Quantity must be greater than 0");
  }

  public BigDecimal getSubtotal() {
    return this.price.multiply(new BigDecimal(this.quantity));
  }

}
