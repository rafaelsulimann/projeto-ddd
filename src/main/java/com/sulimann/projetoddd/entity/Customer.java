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
    this.validate();
  }

  private void validate(){
    if (this.id == null || this.id.isEmpty()) {
      throw new IllegalArgumentException("Id is required");
    }
    if (this.name == null || this.name.isEmpty()) {
      throw new IllegalArgumentException("Name is required");
    }
    if (this.address == null || this.address.isEmpty()) {
      throw new IllegalArgumentException("Address is required");
    }
  }

  public void changeName(String name) {
    this.name = name;
    this.validate();
  }

  public void activate() {
    if(this.address == null || this.address.isEmpty()){
      throw new IllegalArgumentException("Address is mandatory to activate the customer");
    }
    this.active = true;
  }

  public void deactivate() {
    this.active = false;
  }

}
