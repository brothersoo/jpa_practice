package com.brothersoo.jpa_practice.domain;

import static javax.persistence.GenerationType.*;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Vendor {

  @Id
  @GeneratedValue(strategy=IDENTITY)
  @Column(name = "vendor_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "vendor")
  private List<Product> products;

  @Builder
  public Vendor(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
