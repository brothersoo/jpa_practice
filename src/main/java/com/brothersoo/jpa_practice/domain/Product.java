package com.brothersoo.jpa_practice.domain;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "product_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @Column(nullable = false)
  @Enumerated(STRING)
  private ProductType type;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "vendor_id")
  private Vendor vendor;

  @OneToMany(mappedBy = "product")
  private List<Order> orders;

  @Builder
  public Product(Long id, String name, Integer price,
      ProductType type, Vendor vendor) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.type = type;
    this.vendor = vendor;
  }
}
