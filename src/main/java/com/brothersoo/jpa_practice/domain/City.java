package com.brothersoo.jpa_practice.domain;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class City {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "city_id")
  private Long id;

  @Column(nullable = false)
  public String name;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "country_id")
  private Country country;

  @Builder
  public City(Long id, String name, Country country) {
    this.id = id;
    this.name = name;
    this.country = country;
  }
}
