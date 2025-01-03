package com.sulimann.projetoddd.repository;

public interface IDefaultRepository <T> {
  void save(T entity);
  void update(T entity);
  void delete(T entity);
  T findById(String id);
}
