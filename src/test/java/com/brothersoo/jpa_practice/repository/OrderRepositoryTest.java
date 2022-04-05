package com.brothersoo.jpa_practice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.brothersoo.jpa_practice.domain.Order;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTest {

  @Autowired
  OrderRepository orderRepository;

  @Test
  void findAllOrders() {
    List<Order> orders = orderRepository.findAll();
    assertThat(orders).isNotNull();
    System.out.println(orders.get(0).getProduct().getVendor().getName());
  }
}
