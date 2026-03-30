package com.smartgrocery.product.repository;

import com.smartgrocery.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Spring Data JPA magic: This automatically generates a SQL query
    // to search for names containing the string, ignoring case!
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryIgnoreCase(String category);
}
