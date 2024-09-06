package com.api.productos.services;

import com.api.productos.exceptions.ProductNotFoundException;
import com.api.productos.models.ProductModel;
import com.api.productos.repositories.IProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private IProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        ProductModel product1 = new ProductModel();
        ProductModel product2 = new ProductModel();
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductModel> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testCreateProduct() {
        ProductModel newProduct = new ProductModel();
        ProductModel savedProduct = new ProductModel();
        when(productRepository.save(newProduct)).thenReturn(savedProduct);

        ProductModel result = productService.createProduct(newProduct);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productRepository, times(1)).save(newProduct);
    }

    @Test
    public void testGetProductById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductNotFoundException thrown = assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(1L);
        });

        assertEquals("Producto no encontrado", thrown.getMessage());
        verify(productRepository, times(1)).findById(1L);
    }


    @Test
    public void testUpdateProduct() {
        ProductModel existingProduct = new ProductModel();
        ProductModel updatedProduct = new ProductModel();
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        ProductModel result = productService.updateProduct(1L, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        assertEquals(150.0, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    public void testUpdateProduct_NotFound() {
        ProductModel updatedProduct = new ProductModel();
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        ProductModel result = productService.updateProduct(1L, updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(ProductModel.class));
    }

    @Test
    public void testDeleteProduct() {
        when(productRepository.existsById(1L)).thenReturn(true);

        boolean result = productService.deleteProduct(1L);

        assertTrue(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteProduct_NotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        boolean result = productService.deleteProduct(1L);

        assertFalse(result);
        verify(productRepository, times(1)).existsById(1L);
        verify(productRepository, never()).deleteById(1L);
    }
}
