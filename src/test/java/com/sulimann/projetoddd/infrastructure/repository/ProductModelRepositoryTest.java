package com.sulimann.projetoddd.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import com.sulimann.projetoddd.domain.entity.Product;
import com.sulimann.projetoddd.infrastructure.model.ProductModel;

@DataJpaTest
@Import(ProductModelRepository.class)
public class ProductModelRepositoryTest {

  @Autowired
  private ProductModelJPARepository iProductModelRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ProductModelRepository sut;

  @BeforeEach
  void tearDown() {
    iProductModelRepository.deleteAllInBatch();
  }

  @Nested
  class Create {

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> sut.create(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }

    @Test
    @DisplayName("Should persist a product")
    void shouldPersistAProduct() {
      var product = Product.create(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));

      sut.create(product);

      entityManager.flush();
      entityManager.clear();

      var persistedProduct = iProductModelRepository.findById(UUID.fromString(product.getId())).orElse(null);

      assertThat(persistedProduct).isNotNull();
      assertThat(Product.fromModel(persistedProduct)).usingRecursiveComparison().isEqualTo(product);
    }
  }

  @Nested
  class Update {

    @Test
    @DisplayName("Should update a product")
    void shouldUpdateAProduct() {
      var product = Product.create(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      iProductModelRepository.save(ProductModel.fromEntity(product));

      entityManager.flush();
      entityManager.clear();

      var updatedProduct = iProductModelRepository.findById(UUID.fromString(product.getId())).get();

      updatedProduct.setName("new name");
      updatedProduct.setPrice(new BigDecimal("200.00"));

      sut.update(Product.fromModel(updatedProduct));

      entityManager.flush();
      entityManager.clear();

      var actualProduct = iProductModelRepository.findById(UUID.fromString(product.getId())).orElse(null);

      assertThat(actualProduct).isNotNull();
      assertThat(actualProduct).usingRecursiveComparison().isEqualTo(updatedProduct);
    }

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> sut.update(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }
  }

  @Nested
  class Delete {

    @Test
    @DisplayName("Should delete a product")
    void shouldDeleteAProduct() {
      var product = Product.create(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      iProductModelRepository.save(ProductModel.fromEntity(product));

      entityManager.flush();
      entityManager.clear();

      var persistedProduct = iProductModelRepository.findById(UUID.fromString(product.getId())).orElse(null);

      sut.delete(Product.fromModel(persistedProduct));

      entityManager.flush();
      entityManager.clear();

      var deletedProduct = iProductModelRepository.findById(UUID.fromString(product.getId())).orElse(null);

      assertThat(deletedProduct).isNull();
    }

    @Test
    @DisplayName("Should throw exception when product is null")
    void shouldThrowExceptionWhenProductIsNull() {
      assertThatThrownBy(() -> sut.delete(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Product is required");
    }
  }

  @Nested
  class FindById {

    @Test
    @DisplayName("Should return a product by id")
    void shouldReturnAProductById() {
      var product = Product.create(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));
      iProductModelRepository.save(ProductModel.fromEntity(product));

      entityManager.flush();
      entityManager.clear();

      var persistedProduct = sut.findById(product.getId());
      var persistedProductModel = iProductModelRepository.findById(UUID.fromString(product.getId())).orElse(null);

      assertThat(persistedProduct).isPresent();
      assertThat(persistedProduct.get()).usingRecursiveComparison().isEqualTo(product);
      assertThat(persistedProductModel).isNotNull();
      assertThat(ProductModel.fromEntity(persistedProduct.get())).usingRecursiveComparison().isEqualTo(persistedProductModel);
    }

    @Test
    @DisplayName("Should throw exception when id is null")
    void shouldThrowExceptionWhenIdIsNull() {
      assertThatThrownBy(() -> sut.findById(null))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("Id is required");
    }
  }

}
