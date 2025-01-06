package com.sulimann.projetoddd.domain.entity;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;

import com.sulimann.projetoddd.domain.entity.contracts.CustomerContract;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class Customer {

  private String id;
  private String name;
  private Address address;
  private boolean active;

  @Getter(value = AccessLevel.NONE)
  private BigDecimal rewardPoints;

  private Customer(String id, String name, Address address, boolean active, BigDecimal rewardPoints) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.active = active;
    this.rewardPoints = rewardPoints;
    this.validate();
  }

  public static Customer create(String id, String name) {
    return new Customer(
      id,
      name,
      null,
      true,
      BigDecimal.ZERO
    );
  }

  public static Customer fromModel(CustomerContract model) {
    return new Customer(
      model.getId(),
      model.getName(),
      Address.fromModel(model.getAddress()),
      model.isActive(),
      model.getRewardPoints()
    );
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

  public void addRewardPoints(BigDecimal rewardPoints) {
    this.rewardPoints = this.rewardPoints.add(rewardPoints);
  }

  public BigDecimal getRewardPoints() {
    return this.rewardPoints.setScale(2);
  }

}
