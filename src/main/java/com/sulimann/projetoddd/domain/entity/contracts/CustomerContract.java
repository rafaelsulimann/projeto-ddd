package com.sulimann.projetoddd.domain.entity.contracts;

import java.math.BigDecimal;

public interface CustomerContract {

  String getId();
  String getName();
  AddressContract getAddress();
  boolean isActive();
  BigDecimal getRewardPoints();

}
