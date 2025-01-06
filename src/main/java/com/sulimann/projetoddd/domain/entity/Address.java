package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;

import com.sulimann.projetoddd.domain.entity.contracts.AddressContract;

import lombok.Getter;

@Getter
public class Address {

  private String street;
  private String number;
  private String city;
  private String state;
  private String zipCode;

  private Address(String street, String number, String city, String state, String zipCode) {
    this.street = street;
    this.number = number;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.validate();
  }

  public static Address create(String street, String number, String city, String state, String zipCode) {
    return new Address(street, number, city, state, zipCode);
  }

  public static Address fromModel(AddressContract model) {
    return new Address(model.getStreet(), model.getNumber(), model.getCity(), model.getState(), model.getZipCode());
  }

  private void validate(){
    hasText(this.street, "Street is required");
    hasText(this.number, "Number is required");
    hasText(this.city, "City is required");
    hasText(this.state, "State is required");
    hasText(this.zipCode, "ZipCode is required");
  }


}
