package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.isTrue;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

import com.sulimann.projetoddd.domain.entity.contracts.ProductContract;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Product {

  private String id;
  private String name;

  @Getter(value = AccessLevel.NONE)
  private BigDecimal price;

  private Product(String id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.validate();
  }

  public static Product create(String id, String name, BigDecimal price) {
    return new Product(id, name, price);
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

  public void changeName(String newName) {
    this.name = newName;
    this.validate();
  }

  public static Product fromModel(ProductContract model) {
    return new Product(model.getId(), model.getName(), model.getPrice());
  }

}
