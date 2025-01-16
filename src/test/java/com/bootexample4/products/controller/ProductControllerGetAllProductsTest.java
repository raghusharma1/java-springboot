
package com.bootexample4.products.controller;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductControllerGetAllProductsTest {

	@Autowired
	private ProductController productController;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		productController = new ProductController(productRepository);
	}

	@Test
    @Tag("valid")
    public void retrieveEmptyListOfProducts() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        List<Product> products = productController.getAllProducts();
        assertTrue(products.isEmpty(), "The list should be empty");
    }

	@Test
	@Tag("valid")
	public void retrieveNonEmptyListOfProducts() {
		List<Product> mockProducts = new ArrayList<>();
		mockProducts.add(new Product("Product1", "Description1", 10.0));
		mockProducts.add(new Product("Product2", "Description2", 20.0));
		when(productRepository.findAll()).thenReturn(mockProducts);
		List<Product> products = productController.getAllProducts();
		assertEquals(mockProducts, products, "The lists should be identical and contain all products");
	}

	@Test
    @Tag("invalid")
    public void handleRepositoryExceptionWhenRetrievingProducts() {
        when(productRepository.findAll()).thenThrow(new RuntimeException("Database failure"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            productController.getAllProducts();
        });
        assertEquals("Database failure", exception.getMessage(), "The exception message should match the expected one");
    }

}