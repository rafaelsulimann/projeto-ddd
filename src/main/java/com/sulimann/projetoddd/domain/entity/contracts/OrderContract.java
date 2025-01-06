package com.sulimann.projetoddd.domain.entity.contracts;

import java.math.BigDecimal;
import java.util.Set;

public interface OrderContract {

  String getId();
  String getCustomerId();
  Set<? extends OrderItemContract> getItems();
  BigDecimal getTotal();

}
