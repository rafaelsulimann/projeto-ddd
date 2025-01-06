package com.sulimann.projetoddd.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.sulimann.projetoddd.domain.entity.Product;

public class ProductServiceTest {

  @Nested
  class IncreasePrice {

    @Test
    @DisplayName("Should update price of all products")
    void shouldUpdatePriceOfAllProducts() {
      var product1 = Product.create("1", "Product 1", new BigDecimal("100.00"));
      var product2 = Product.create("2", "Product 2", new BigDecimal("200.00"));

      ProductService.increasePrice(Set.of(product1, product2), new BigDecimal("100.00"));

      assertThat(product1.getPrice()).isEqualTo(new BigDecimal("200.00"));
      assertThat(product2.getPrice()).isEqualTo(new BigDecimal("400.00"));
    }

    @Test
    @DisplayName("Should throw exception when list of products is empty")
    void shouldThrowExceptionWhenListOfProductsIsEmpty() {
      assertThatThrownBy(() -> ProductService.increasePrice(Set.of(), new BigDecimal("100.00")))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("List of products is required");
    }

    @Test
    @DisplayName("Should throw exception when percentage is null")
    void shouldThrowExceptionWhenPercentageIsNull() {
      var product = Product.create("1", "Product 1", new BigDecimal("100.00"));

      assertThatThrownBy(() -> ProductService.increasePrice(Set.of(product), null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Percentage is required");
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when percentage is less than zero")
    @ValueSource(strings = { "-1", "0" })
    void shouldThrowExceptionWhenPercentageIsLessThanZero(String percentage) {
      var product = Product.create("1", "Product 1", new BigDecimal("100.00"));

      assertThatThrownBy(() -> ProductService.increasePrice(Set.of(product), new BigDecimal(percentage)))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Percentage must be greater than zero");
    }
  }

}
