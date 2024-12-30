package com.sulimann.projetoddd.service;

import static com.sulimann.projetoddd.shared.AssertUtils.isFalse;
import static com.sulimann.projetoddd.shared.AssertUtils.isTrue;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.math.BigDecimal;
import java.util.Set;

import com.sulimann.projetoddd.entity.Product;

public class ProductService {

  public static void increasePrice(Set<Product> products, BigDecimal percentage) {
    validateIncreasePrice(products, percentage);
    products.forEach(product -> {
      var newPrice = product.getPrice()
          .add(percentage.divide(new BigDecimal("100.0")).multiply(product.getPrice()));

      product.changePrice(newPrice);
    });
  }

  private static void validateIncreasePrice(Set<Product> products, BigDecimal percentage) {
    isFalse(products.isEmpty(), "List of products is required");
    notNull(percentage, "Percentage is required");
    isTrue(percentage.compareTo(BigDecimal.ZERO) > 0, "Percentage must be greater than zero");
  }

}
