package com.sulimann.projetoddd.infrastructure.model;

import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import com.sulimann.projetoddd.domain.entity.Address;
import com.sulimann.projetoddd.domain.entity.contracts.AddressContract;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel implements AddressContract {

  private String street;
  private String number;
  private String city;
  private String state;
  private String zipCode;

  public static AddressModel fromEntity(Address address) {
    notNull(address, "Address is required");

    return new AddressModel(
      address.getStreet(),
      address.getNumber(),
      address.getCity(),
      address.getState(),
      address.getZipCode()
    );
  }


}
