package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isTrue;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

public class Product {

  private String id;
  private String name;
  private BigDecimal price;

  public Product(String id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.validate();
  }

  private void validate() {
    hasText(this.id, "Id is required");
    hasText(this.name, "Name is required");
    notNull(this.price, "Price is required");
    isTrue(this.price.compareTo(BigDecimal.ZERO) > 0, "Price must be greater than zero");
  }

  public BigDecimal getPrice() {
    return price.setScale(2);
  }

  public void changePrice(BigDecimal newPrice) {
    this.price = newPrice;
    this.validate();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
