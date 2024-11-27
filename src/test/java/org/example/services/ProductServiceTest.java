package org.example.services;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.example.models.Product;
import org.example.repository.ProductRepository;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    private ProductRepository mockRepository;
    private ProductService productService;
    private Product product;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(mockRepository);
        product = new Product(1L, "Test Product", 100.0);
    }

    @Test
    void shouldAddProductSuccess() {
        productService.addProduct(product);

        verify(mockRepository, times(1)).save(product);
    }

    @Test
    void shouldAddProductError() {
        doThrow(new RuntimeException("Database error")).when(mockRepository).save(product);

        RuntimeException exception = assertThrows(
                RuntimeException.class, () -> productService.addProduct(product)
        );

        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void shouldGetProductByIdSuccess() {
        when(mockRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertEquals(product, result);
        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    void shouldUpdateProductSuccess() {
        productService.updateProduct(product);

        verify(mockRepository, times(1)).save(product);
    }
}
