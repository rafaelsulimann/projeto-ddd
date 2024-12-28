package com.sulimann.projetoddd.entity;

public class Customer {

  private String id;
  private String name;
  private String address;
  private boolean active;

  public Customer(String id, String name, String address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  public void changeName(String name) {
    this.name = name;
  }

  public void activate() {
    this.active = true;
  }

  public void deactivate() {
    this.active = false;
  }

}
