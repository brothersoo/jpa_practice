package com.brothersoo.jpa_practice.domain;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

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
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Country {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "country_id")
  private Long id;

  @Column(nullable = false)
  private String name;

  @OneToMany(mappedBy = "country")
  private List<City> cities;

  @Builder
  public Country(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
