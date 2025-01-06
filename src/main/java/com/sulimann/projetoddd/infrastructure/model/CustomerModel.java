package com.sulimann.projetoddd.infrastructure.model;

import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.sulimann.projetoddd.domain.entity.Customer;
import com.sulimann.projetoddd.domain.entity.contracts.CustomerContract;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_customers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel implements CustomerContract {

  @Id
  private UUID id;
  private String name;

  @Embedded
  private AddressModel address;

  private boolean active;
  private BigDecimal rewardPoints;

  private Instant createdAt;
  private Instant updatedAt;

  @Override
  public String getId() {
    return this.id.toString();
  }

  public static CustomerModel fromEntity(Customer entity) {
    notNull(entity, "Customer is required");

    return new CustomerModel(
      UUID.fromString(entity.getId()),
      entity.getName(),
      AddressModel.fromEntity(entity.getAddress()),
      entity.isActive(),
      entity.getRewardPoints(),
      Instant.now(),
      Instant.now()
    );
  }


}
