package com.brothersoo.jpa_practice.repository;

import com.brothersoo.jpa_practice.domain.Vendor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class VendorRepositoryImpl {

  @PersistenceContext
  EntityManager em;

  public Vendor save(Vendor vendor) {
    em.persist(vendor);
    return vendor;
  }

  public Vendor findById(Long id) {
    return em.find(Vendor.class, id);
  }

  public List<Vendor> findAll() {
    return em.createQuery("SELECT v FROM Vendor v", Vendor.class)
        .getResultList();
  }

  public void detach(Vendor vendor) {
    em.detach(vendor);
  }
}
