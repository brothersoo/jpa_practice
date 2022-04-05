package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

  @PersistenceContext
  EntityManager em;

  @Override
  public Order save(Order order) {
    em.persist(order);
    return order;
  }

  @Override
  public List<Order> findAll() {
    return em.createQuery("SELECT o FROM Order o", Order.class).getResultList();
  }

  @Override
  public Order findById(Long id) {
    return em.find(Order.class, id);
  }

  @Override
  public void deleteById(Long id) {
    em.remove(findById(id));
  }
}
