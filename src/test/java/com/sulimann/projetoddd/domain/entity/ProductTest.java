package com.sulimann.projetoddd.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProductTest {

  @Nested
  class Constructor {

    @Test
    @DisplayName("Should throw exception when id is empty")
    void shouldThrowExceptionWhenIdIsEmpty() {
      assertThatThrownBy(() -> Product.create("", "Product 1", new BigDecimal("100.00")))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
      assertThatThrownBy(() -> Product.create("1", "", new BigDecimal("100.00")))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void shouldThrowExceptionWhenPriceIsNull() {
      assertThatThrownBy(() -> Product.create("1", "Product 1", null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price is required");
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when price is less than zero")
    @ValueSource(strings = { "-1", "0" })
    void shouldThrowExceptionWhenPriceIsLessThanZero(String price) {
      assertThatThrownBy(() -> Product.create("1", "Product 1", new BigDecimal(price)))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price must be greater than zero");
    }
  }

  @Nested
  class ChangePrice {

    @Test
    @DisplayName("Should update price")
    void shouldUpdatePrice() {
      var product = Product.create("1", "Product 1", new BigDecimal("100.00"));

      product.changePrice(new BigDecimal("200.00"));

      assertThat(product.getPrice()).isEqualTo(new BigDecimal("200.00"));
    }

    @Test
    @DisplayName("Should throw exception when price is null")
    void shouldThrowExceptionWhenPriceIsNull() {
      assertThatThrownBy(() -> Product.create("1", "Product 1", null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price is required");
    }

    @ParameterizedTest
    @DisplayName("Should throw exception when price is less than zero")
    @ValueSource(strings = { "-1", "0" })
    void shouldThrowExceptionWhenPriceIsLessThanZero(String price) {
      assertThatThrownBy(() -> Product.create("1", "Product 1", new BigDecimal(price)))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Price must be greater than zero");
    }

  }

  @Nested
  class ChangeName {

    @Test
    @DisplayName("Should update name")
    void shouldUpdateName() {
      var product = Product.create("1", "Product 1", new BigDecimal("100.00"));

      product.changeName("Product 2");

      assertThat(product.getName()).isEqualTo("Product 2");
    }

    @Test
    @DisplayName("Should throw exception when name is empty")
    void shouldThrowExceptionWhenNameIsEmpty() {
      assertThatThrownBy(() -> Product.create("1", "", new BigDecimal("100.00"))
      ).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Name is required");
    }
  }

  @Nested
  class GetPrice {

    @Test
    @DisplayName("Should return the price")
    void shouldReturnThePrice() {
      var product = Product.create("1", "Product 1", new BigDecimal("100.00"));

      var price = product.getPrice();

      assertThat(price).isEqualTo(new BigDecimal("100.00"));
    }
  }

}
