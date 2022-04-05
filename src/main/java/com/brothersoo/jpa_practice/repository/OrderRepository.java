package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.Order;
import java.util.List;

public interface OrderRepository {

  Order save(Order order);

  List<Order> findAll();

  Order findById(Long id);

  void deleteById(Long id);
}
