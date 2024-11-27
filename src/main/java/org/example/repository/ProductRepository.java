package org.example.repository;
import org.example.models.Product;

import java.util.Optional;

public interface ProductRepository {
    void save(Product product);
    Optional<Product> findById(Long id);
}
