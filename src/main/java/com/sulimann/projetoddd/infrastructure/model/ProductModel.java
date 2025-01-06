package com.sulimann.projetoddd.infrastructure.model;

import java.math.BigDecimal;
import java.util.UUID;

import com.sulimann.projetoddd.domain.entity.Product;
import com.sulimann.projetoddd.domain.entity.contracts.ProductContract;

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
public class ProductModel implements ProductContract{

  @Id
  private UUID id;
  private String name;
  private BigDecimal price;

  public static ProductModel fromEntity(Product entity) {
    return new ProductModel(UUID.fromString(entity.getId()), entity.getName(), entity.getPrice());
  }

  @Override
  public String getId(){
    return this.id.toString();
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

}
