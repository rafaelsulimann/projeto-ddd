package com.sulimann.projetoddd.domain.repository;

public interface IDefaultRepository <T> {
  void create(T entity);
  void update(T entity);
  void delete(T entity);
  T findById(String id);
}
