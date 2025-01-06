package com.sulimann.projetoddd.domain.entity.contracts;

import java.math.BigDecimal;

public interface OrderItemContract {

  String getId();
  String getProductId();
  String getName();
  BigDecimal getPrice();
  int getQuantity();
}
