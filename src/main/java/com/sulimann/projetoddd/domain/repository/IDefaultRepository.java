package com.sulimann.projetoddd.domain.repository;

import java.util.Optional;

public interface IDefaultRepository <T> {
  void create(T entity);
  void update(T entity);
  void delete(T entity);
  Optional<T> findById(String id);
}
