package com.sulimann.projetoddd.entity;

import java.util.Set;

public class Order {

  private String id;
  private String customerId;
  private Set<OrderItem> items;

  public Order(String id, String customerId, Set<OrderItem> items) {
    this.id = id;
    this.customerId = customerId;
    this.items = items;
  }

}
