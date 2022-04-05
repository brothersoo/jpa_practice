package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.User;
import java.util.List;

public interface UserRepository {

  User save(User user);

  User findById(long id);

  User findByEmail(String email);

  List<User> findAllMembers();

  List<User> findAllAdmins();

  List<User> findAll();

  void deleteById(Long id);
}
