package com.sulimann.projetoddd.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

public class Customer {

  private String id;
  private String name;
  private Address address;
  private boolean active = false;
  private BigDecimal rewardPoints = BigDecimal.ZERO;

  public Customer(String id, String name) {
    this.id = id;
    this.name = name;
    this.validate();
  }

  private void validate(){
    hasText(this.id, "Id is required");
    hasText(this.name, "Name is required");
  }

  public void changeName(String name) {
    this.name = name;
    this.validate();
  }

  public void activate() {
    notNull(this.address, "Address is mandatory to activate the customer");
    this.active = true;
  }

  public void deactivate() {
    this.active = false;
  }

  public void setAddress(Address address) {
    notNull(address, "Address is required");
    this.address = address;
  }

  public String getName() {
    return this.name;
  }

  public Address getAddress() {
    return this.address;
  }

  public boolean isActive() {
    return this.active;
  }

}
