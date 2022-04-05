package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.Product;
import java.util.List;

public interface ProductRepository {

  Product save(Product product);

  Product findById(Long id);

  List<Product> findAll();

  List<Product> findByName(String name);

  void deleteById(Long id);
}
