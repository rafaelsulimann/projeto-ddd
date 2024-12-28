package com.sulimann.projetoddd.entity;

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
  }

}
