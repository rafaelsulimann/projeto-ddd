package com.sulimann.projetoddd.entity;

public class Address {

  private String street;
  private String number;
  private String city;
  private String state;
  private String zipCode;

  public Address(String street, String number, String city, String state, String zipCode) {
    this.street = street;
    this.number = number;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.validate();
  }

  private void validate(){
    if (this.street == null || this.street.isEmpty()) {
      throw new IllegalArgumentException("Street is required");
    }
    if (this.number == null || this.number.isEmpty()) {
      throw new IllegalArgumentException("Number is required");
    }
    if (this.city == null || this.city.isEmpty()) {
      throw new IllegalArgumentException("City is required");
    }
    if (this.state == null || this.state.isEmpty()) {
      throw new IllegalArgumentException("State is required");
    }
    if (this.zipCode == null || this.zipCode.isEmpty()) {
      throw new IllegalArgumentException("ZipCode is required");
    }
  }
}
