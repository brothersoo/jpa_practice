package com.brothersoo.jpa_practice.controller;

import com.brothersoo.jpa_practice.domain.Gender;
import com.brothersoo.jpa_practice.domain.Order;
import com.brothersoo.jpa_practice.domain.Product;
import com.brothersoo.jpa_practice.domain.ProductType;
import com.brothersoo.jpa_practice.domain.User;
import com.brothersoo.jpa_practice.domain.UserType;
import com.brothersoo.jpa_practice.domain.Vendor;
import com.brothersoo.jpa_practice.repository.VendorRepositoryImpl;
import com.github.javafaker.Faker;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.brothersoo.jpa_practice.repository.OrderRepository;
import com.brothersoo.jpa_practice.repository.ProductRepository;
import com.brothersoo.jpa_practice.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final OrderRepository orderRepository;
  private final VendorRepositoryImpl vendorRepository;

  @GetMapping("/")
  public ResponseEntity<String> index() {
    return new ResponseEntity("data index", HttpStatus.OK);
  }

  @GetMapping("/createNUsers")
  @Transactional
  public ResponseEntity<String> createNUsers(@RequestParam(value="number") int number) {
    Faker faker = new Faker();

    for (int i = 0; i < number / 5; i++) {
      StringBuilder nickname = new StringBuilder()
          .append(faker.name().prefix()).append(' ')
          .append(faker.name().username()).append(' ')
          .append(faker.name().suffix());
      User user = User.builder()
          .email(faker.internet().emailAddress())
          .password(faker.crypto().sha256())
          .nickname(nickname.toString())
          .gender(faker.bool().bool() ? Gender.MALE : Gender.FEMALE)
          .type(UserType.ADMIN)
          .build();
      userRepository.save(user);
    }
    for (int i = number / 5 + 1; i < number; i++) {
      StringBuilder nickname = new StringBuilder()
          .append(faker.name().prefix()).append(' ')
          .append(faker.name().username()).append(' ')
          .append(faker.name().suffix());
      User user = User.builder()
          .email(faker.internet().emailAddress())
          .password(faker.crypto().sha256())
          .nickname(nickname.toString())
          .gender(faker.bool().bool() ? Gender.MALE : Gender.FEMALE)
          .type(UserType.MEMBER)
          .build();
      userRepository.save(user);
    }

    return new ResponseEntity("created n users", HttpStatus.OK);
  }

  @GetMapping("/createNVendors")
  @Transactional
  public ResponseEntity<String> createNVendors(@RequestParam(value="number") int number) {
    Faker faker = new Faker();
    for (int i = 0; i < number; i++) {
      StringBuilder name = new StringBuilder()
          .append(faker.company().name()).append(' ')
          .append(faker.company().suffix());
      Vendor vendor = Vendor.builder()
          .name(name.toString())
          .build();
      vendorRepository.save(vendor);
    }

    return new ResponseEntity("created n vendors", HttpStatus.OK);
  }

  @GetMapping("/createNProducts")
  @Transactional
  public ResponseEntity<String> createNProducts(@RequestParam(value="number") int number) {
    Faker faker = new Faker();

    List<Vendor> vendors = vendorRepository.findAll();
    int vendorCnt = vendors.size();

    for (int i = 0; i < number; i++) {
      StringBuilder name = new StringBuilder()
          .append(faker.esports().team()).append(' ')
          .append(faker.pokemon().name()).append(' ')
          .append(faker.name().suffix());
      Product product = Product.builder()
          .name(name.toString())
          .price((faker.number().numberBetween(1000, 100_000_000) / 1000) * 1000)
          .type(faker.bool().bool() ? ProductType.ELECTRONICS : ProductType.FOOD)
          .vendor(vendors.get(faker.number().numberBetween(0, vendorCnt - 1)))
          .build();
      productRepository.save(product);
    }

    return new ResponseEntity("created n products", HttpStatus.OK);
  }

  @GetMapping("/createNOrders")
  @Transactional
  public ResponseEntity<String> createNOrders(@RequestParam(value="number") int number) {
    Faker faker = new Faker();

    List<User> users = userRepository.findAll();
    int usersCnt = users.size();
    List<Product> products = productRepository.findAll();
    int productsCnt = products.size();

    for (int i = 0; i < number; i++) {
      Order order = Order.builder()
          .amount(faker.number().numberBetween(1, 10))
          .user(users.get(faker.number().numberBetween(0, usersCnt - 1)))
          .product(products.get(faker.number().numberBetween(0, productsCnt - 1)))
          .build();
      orderRepository.save(order);
    }

    return new ResponseEntity<>("created n orders", HttpStatus.OK);
  }
}
