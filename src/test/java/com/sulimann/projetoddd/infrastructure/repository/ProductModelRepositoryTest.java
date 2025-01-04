package com.sulimann.projetoddd.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.sulimann.projetoddd.domain.entity.Product;
import com.sulimann.projetoddd.infrastructure.model.ProductModel;

@DataJpaTest
public class ProductModelRepositoryTest {

  @Autowired
  private IProductModelRepository iProductModelRepository;

  @Autowired
  private TestEntityManager entityManager;

  private ProductModelRepository productModelRepository;

  @BeforeEach
  void setup() {
    productModelRepository = new ProductModelRepository(iProductModelRepository);
  }

  @AfterEach
  void tearDown() {
    iProductModelRepository.deleteAll();
  }

  @Nested
  class Create {

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> productModelRepository.create(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }

    @Test
    @DisplayName("Should persist a product")
    void shouldPersistAProduct() {
      var product = new Product(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));

      productModelRepository.create(product);
      var persistedProduct = entityManager.find(ProductModel.class, UUID.fromString(product.getId()));

      assertThat(persistedProduct).isNotNull();
      assertThat(persistedProduct.toEntity()).usingRecursiveComparison().isEqualTo(product);
    }
  }

  @Nested
  class Update {

    @Test
    @DisplayName("Should update a product")
    void shouldUpdateAProduct() {
      var product = new Product(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      productModelRepository.create(product);

      product.changeName("new name");
      product.changePrice(new BigDecimal("200.00"));
      productModelRepository.update(product);

      var persistedProduct = entityManager.find(ProductModel.class, UUID.fromString(product.getId()));

      assertThat(persistedProduct).isNotNull();
      assertThat(persistedProduct.toEntity()).usingRecursiveComparison().isEqualTo(product);
    }

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> productModelRepository.update(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }
  }

  @Nested
  class Delete {

    @Test
    @DisplayName("Should delete a product")
    void shouldDeleteAProduct() {
      var product = new Product(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      productModelRepository.create(product);

      productModelRepository.delete(product);

      var persistedProduct = entityManager.find(ProductModel.class, UUID.fromString(product.getId()));

      assertThat(persistedProduct).isNull();
    }

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> productModelRepository.delete(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }
  }

  @Nested
  class FindById {

    @Test
    @DisplayName("Should return a product by id")
    void shouldReturnAProductById() {
      var product = new Product(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      productModelRepository.create(product);

      var persistedProduct = productModelRepository.findById(product.getId());
      var persistedProductModel = entityManager.find(ProductModel.class, UUID.fromString(product.getId()));

      assertThat(persistedProduct).isPresent();
      assertThat(persistedProduct.get()).usingRecursiveComparison().isEqualTo(product);
      assertThat(persistedProductModel).isNotNull();
      assertThat(ProductModel.fromEntity(persistedProduct.get())).usingRecursiveComparison().isEqualTo(persistedProductModel);
    }

    @Test
    @DisplayName("Should throw exception when id is null")
    void shouldThrowExceptionWhenIdIsNull() {
      assertThatThrownBy(() -> productModelRepository.findById(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }
  }

}
