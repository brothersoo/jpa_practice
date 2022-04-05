package com.brothersoo.jpa_practice.lifecycle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.brothersoo.jpa_practice.domain.Vendor;
import com.brothersoo.jpa_practice.repository.VendorRepositoryImpl;
import javax.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class LifeCycleTest {

  @Autowired
  VendorRepositoryImpl vendorRepository;

  @Test
  @Transactional
  void detachedTest() {
    Vendor vendor1 = Vendor.builder()
        .name("Super Cool Vendor")
        .build();

    vendorRepository.save(vendor1);

    vendorRepository.detach(vendor1);

    Vendor vendor2 = vendorRepository.findById(vendor1.getId());

    assertEquals(vendor2.getName(), "Super Cool Vendor");
    Assertions.assertThat(vendor2).isNotEqualTo(vendor1);

    Vendor vendor3 = vendorRepository.findById(vendor1.getId());

    Assertions.assertThat(vendor3).isEqualTo(vendor2);
  }
}
