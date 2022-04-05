package com.brothersoo.jpa_practice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.brothersoo.jpa_practice.domain.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

  @Autowired
  UserRepository userRepository;

  @Test
  void findAllUsers() {
    List<User> users = userRepository.findAll();
    assertThat(users).isNotNull();
  }
}
