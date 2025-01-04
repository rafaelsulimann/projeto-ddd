package com.sulimann.projetoddd.infrastructure.repository;

import static com.sulimann.projetoddd.shared.AssertUtils.hasText;
import static com.sulimann.projetoddd.shared.AssertUtils.notNull;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sulimann.projetoddd.domain.entity.Product;
import com.sulimann.projetoddd.domain.repository.IProductRepository;
import com.sulimann.projetoddd.infrastructure.model.ProductModel;

@Repository
public class ProductModelRepository implements IProductRepository{

  private final IProductModelRepository productModelRepository;

  public ProductModelRepository(IProductModelRepository productModelRepository) {
    this.productModelRepository = productModelRepository;
  }

  @Override
  public void create(Product entity) {
    notNull(entity, "Product is required");
    this.productModelRepository.save(ProductModel.fromEntity(entity));
  }

  @Override
  public void update(Product entity) {
    notNull(entity, "Product is required");
    this.productModelRepository.save(ProductModel.fromEntity(entity));
  }

  @Override
  public void delete(Product entity) {
    notNull(entity, "Product is required");
    this.productModelRepository.delete(ProductModel.fromEntity(entity));
  }

  @Override
  public Optional<Product> findById(String id) {
    hasText(id, "Id is required");
    return this.productModelRepository.findById(UUID.fromString(id)).map(ProductModel::toEntity);
  }

}
