package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

  @PersistenceContext
  EntityManager em;

  @Override
  public Product save(Product product) {
    em.persist(product);
    return product;
  }

  @Override
  public Product findById(Long id) {
    return em.find(Product.class, id);
  }

  @Override
  public List<Product> findAll() {
    return em.createQuery("SELECT p FROM Product p", Product.class)
        .getResultList();
  }

  @Override
  public List<Product> findByName(String name) {
    return em.createQuery("SELECT p FROM Product p WHERE p.name like %:name%", Product.class)
        .setParameter("name", name)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {
    em.remove(findById(id));
  }
}
