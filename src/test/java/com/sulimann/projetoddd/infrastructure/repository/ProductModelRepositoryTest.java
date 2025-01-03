package com.sulimann.projetoddd.infrastructure.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

  @Test
  void shouldPersistAProduct() {
    var product = new Product(UUID.randomUUID().toString(), "name", new BigDecimal("100.00"));

    this.productModelRepository.create(product);
    var persistedProduct = this.entityManager.find(ProductModel.class, UUID.fromString(product.getId()));

    assertThat(persistedProduct).isNotNull();
    assertThat(persistedProduct.getId()).isEqualTo(UUID.fromString(product.getId()));
    assertThat(persistedProduct.getName()).isEqualTo(product.getName());
    assertThat(persistedProduct.getPrice()).isEqualTo(product.getPrice());
  }
}
