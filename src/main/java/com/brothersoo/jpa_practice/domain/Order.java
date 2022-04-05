package com.brothersoo.jpa_practice.domain;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name="\"Order\"")
public class Order extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "order_id")
  private Long id;

  @Column(nullable = false)
  int amount;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = EAGER)
  @JoinColumn(name = "product_id")
  private Product product;

  @Builder
  public Order(Long id, int amount, User user, Product product) {
    this.id = id;
    this.amount = amount;
    this.user = user;
    this.product = product;
  }
}
