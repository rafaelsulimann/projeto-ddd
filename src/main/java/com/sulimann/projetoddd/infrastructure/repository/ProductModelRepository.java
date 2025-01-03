package com.sulimann.projetoddd.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sulimann.projetoddd.domain.entity.Product;
import com.sulimann.projetoddd.domain.repository.IProductRepository;
import com.sulimann.projetoddd.infrastructure.model.ProductModel;

@Repository
public class ProductModelRepository implements IProductRepository{

  @Autowired
  private final IProductModelRepository productModelRepository;

  public ProductModelRepository(IProductModelRepository productModelRepository) {
    this.productModelRepository = productModelRepository;
  }

  @Override
  public void create(Product entity) {
    this.productModelRepository.save(ProductModel.from(entity));
  }

  @Override
  public void update(Product entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Product entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Product findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

}
