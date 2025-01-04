package com.sulimann.projetoddd.infrastructure.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.sulimann.projetoddd.domain.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tb_products")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductModel {

  @Id
  private UUID id;
  private String name;
  private BigDecimal price;

  public static ProductModel from(Product entity) {
    return new ProductModel(UUID.fromString(entity.getId()), entity.getName(), entity.getPrice());
  }

  public Product toEntity() {
    return new Product(this.id.toString(), this.name, this.price);
  }

}
