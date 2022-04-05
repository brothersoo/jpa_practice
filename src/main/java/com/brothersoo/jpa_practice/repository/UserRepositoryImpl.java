package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.User;
import com.brothersoo.jpa_practice.domain.UserType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @PersistenceContext
  private EntityManager em;

  public User save(User user) {
    em.persist(user);
    return user;
  }

  @Override
  public User findById(long id) {
    return em.find(User.class, id);
  }

  @Override
  public User findByEmail(String email) {
    return (User) em.createQuery("SELECT u FROM User u WHERE u.email = :email")
        .setParameter("email", email)
        .getResultList().get(0);
  }

  @Override
  public List<User> findAllMembers() {
    return em.createQuery("SELECT u FROM User u WHERE u.type = :type", User.class)
        .setParameter("type", UserType.MEMBER)
        .getResultList();
  }

  @Override
  public List<User> findAllAdmins() {
    return em.createQuery("SELECT u FROM User u WHERE u.type = :type", User.class)
        .setParameter("type", UserType.ADMIN)
        .getResultList();
  }

  @Override
  public List<User> findAll() {
    return em.createQuery("SELECT u FROM User u", User.class)
        .getResultList();
  }

  @Override
  public void deleteById(Long id) {
    em.remove(findById(id));
  }
}
