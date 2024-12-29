package com.sulimann.projetoddd.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;

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
    hasText(this.street, "Street is required");
    hasText(this.number, "Number is required");
    hasText(this.city, "City is required");
    hasText(this.state, "State is required");
    hasText(this.zipCode, "ZipCode is required");
  }

  public String getStreet() {
    return street;
  }

  public String getNumber() {
    return number;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getZipCode() {
    return zipCode;
  }
}
