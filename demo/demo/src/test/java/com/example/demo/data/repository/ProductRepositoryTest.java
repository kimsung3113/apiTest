package com.example.demo.data.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.example.demo.data.entity.Product;

import jakarta.transaction.Transactional;

@SpringBootTest
class ProductRepositoryTest {

  @Autowired ProductRepository productRepository;
  
  // 메서드안에 규칙으로 Entity의 필드 값을 써야한다. Ex. findByProductName
  
  // Test전에 먼저 실행되어 test데이터를 만들어준다.
  @BeforeEach
  void GenerateData() {
    int count = 1;
    productRepository.save(getProduct(Integer.toString(count), count++, 2000, 3000));
    productRepository.save(getProduct(Integer.toString(count), count++, 3000, 3000));
    productRepository.save(getProduct(Integer.toString(--count), count = count + 2, 1500, 200));
    productRepository.save(getProduct(Integer.toString(count), count++, 4000, 3000));
    productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1500));
    productRepository.save(getProduct(Integer.toString(count), count++, 10000, 1000));
    productRepository.save(getProduct(Integer.toString(count), count++, 500, 10000));
    productRepository.save(getProduct(Integer.toString(count), count++, 8500, 3500));
    productRepository.save(getProduct(Integer.toString(count), count++, 1000, 2000));
    productRepository.save(getProduct(Integer.toString(count), count, 5100, 1700));
  }

  private Product getProduct(String id, int nameNumber, int price, int stock) {
    return new Product(id, "상품" + nameNumber, price, stock);
  }

  @Test
  void findTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundEntities = productRepository.findByName("상품4");

    for (Product product : foundEntities) {
      System.out.println(product.toString());
    }

    List<Product> queryEntities = productRepository.queryByName("상품4");

    for (Product product : queryEntities) {
      System.out.println(product.toString());
    }
  }

  @Test
  void existTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.existsByName("상품4"));
    System.out.println(productRepository.existsByName("상품2"));
  }

  @Test
  void countTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.countByName("상품4"));
  }

  @Test
  @Transactional
  // Delete는 특이하다.Spring Data JPA repository를 사용하면 기본적으로 transaction이 유지가 되지 않는다.
  // 이유는? 각 메서드에서 Transaction이 생성이 됬다가 없어지는 이 과정을 반복하고 있기때문에
  // 영속성 context에서 값을 가져오려면 transactional을 켜놔야지만 동작이 가능하다.
  void deleteTest() {
    System.out.println("before : " + productRepository.count());

    productRepository.deleteByName("상품1");
    productRepository.removeByName("상품9");

    System.out.println("After : " + productRepository.count());
  }

  @Test
  void topTest() {
    productRepository.save(getProduct("109", 123, 1500, 5000));
    productRepository.save(getProduct("101", 123, 2500, 5000));
    productRepository.save(getProduct("102", 123, 3500, 5000));
    productRepository.save(getProduct("103", 123, 4500, 5000));
    productRepository.save(getProduct("104", 123, 1000, 5000));
    productRepository.save(getProduct("105", 123, 2000, 5000));
    productRepository.save(getProduct("106", 123, 3000, 5000));
    productRepository.save(getProduct("107", 123, 4000, 5000));

    // Top과 first5by 메서드들은 별도의 정렬 쿼리가 정의 되어 있지 않으면 id를 기준으로 오름차순 정렬된 다음에 값을 뽑는다.
    
    List<Product> foundEntities = productRepository.findFirst5ByName("상품123");
    for (Product product : foundEntities) {
      System.out.println(product.toString());
    }

    List<Product> foundEntities2 = productRepository.findTop3ByName("상품123");
    for (Product product : foundEntities2) {
      System.out.println(product.toString());
    }
  }

  /* ↓↓ 조건자 키워드 테스트 ↓↓ */

  @Test
  void isEqualsTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.findByIdIs("1"));
    System.out.println(productRepository.findByIdEquals("1"));
  }

  @Test
  void notTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundEntities = productRepository.findByIdNot("1");
    for (Product product : foundEntities) {
      System.out.println(product);
    }
    // System.out.println(productRepository.findByProductIdNot("1"));
    System.out.println(productRepository.findByIdIsNot("1"));
  }

  @Test
  void nullTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.findByStockIsNull());
    System.out.println(productRepository.findByStockIsNotNull());
  }

  @Test
  void andTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.findTopByIdAndName("1", "상품1"));
  }

  @Test
  void greaterTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> productEntities = productRepository.findByPriceGreaterThan(5000);

    for (Product product : productEntities) {
      System.out.println(product);
    }
  }

  @Test
  void containTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    System.out.println(productRepository.findByNameContaining("상품1"));
  }
  
  
  /* 정렬과 페이징 */
  @Test
  void orderByTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByNameContainingOrderByStockAsc("상품");
    for (Product product : foundProducts) {
      System.out.println(product);
    }

    foundProducts = productRepository.findByNameContainingOrderByStockDesc("상품");
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  void multiOrderByTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts =
        productRepository.findByNameContainingOrderByPriceAscStockDesc("상품");
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  void orderByWithParameterTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts =
        productRepository.findByNameContaining("상품", Sort.by(Order.asc("price")));
    for (Product product : foundProducts) {
      System.out.println(product);
    }

    foundProducts =
        productRepository.findByNameContaining(
            "상품", Sort.by(Order.asc("price"), Order.asc("stock")));
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  void pagingTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts =
        productRepository.findByPriceGreaterThan(200, PageRequest.of(0, 2));
    for (Product product : foundProducts) {
      System.out.println(product);
    }

    // PageRequest.of(페이지의 수, 한페이지 데이터 수)
    foundProducts = productRepository.findByPriceGreaterThan(200, PageRequest.of(5, 2));
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }
  
  /* @Query 사용 Test */
  
  @Test
  public void queryTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByPriceBasis();
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void nativeQueryTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByPriceBasisNativeQuery();
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void parameterQueryTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByPriceWithParameter(2000);
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void parameterNamingQueryTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByPriceWithParameterNaming(2000);
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void parameterNamingQueryTest2() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts = productRepository.findByPriceWithParameterNaming2(2000);
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void nativeQueryPagingTest() {
    List<Product> foundAll = productRepository.findAll();
    System.out.println("====↓↓ Test Data ↓↓====");
    for (Product product : foundAll) {
      System.out.println(product.toString());
    }
    System.out.println("====↑↑ Test Data ↑↑====");

    List<Product> foundProducts =
        productRepository.findByPriceWithParameterPaging(2000, PageRequest.of(2, 2));
    for (Product product : foundProducts) {
      System.out.println(product);
    }
  }

  @Test
  public void basicCRUDTest() {
    /* create */
    // given
    Product product =
        Product.builder().id("testProduct").name("testP").price(1000).stock(500).build();

    // when
    Product savedEntity = productRepository.save(product);

    // then
    Assertions.assertThat(savedEntity.getId()).isEqualTo(product.getId());
    Assertions.assertThat(savedEntity.getName()).isEqualTo(product.getName());
    Assertions.assertThat(savedEntity.getPrice()).isEqualTo(product.getPrice());
    Assertions.assertThat(savedEntity.getStock()).isEqualTo(product.getStock());

    /* read */
    // when
    Product selectedEntity =
        productRepository.findById("testProduct").orElseThrow(RuntimeException::new);

    // then
    Assertions.assertThat(selectedEntity.getId()).isEqualTo(product.getId());
    Assertions.assertThat(selectedEntity.getName()).isEqualTo(product.getName());
    Assertions.assertThat(selectedEntity.getPrice()).isEqualTo(product.getPrice());
    Assertions.assertThat(selectedEntity.getStock()).isEqualTo(product.getStock());
  }
  
  
}
