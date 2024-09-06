package com.api.productos.services;

import com.api.productos.exceptions.ProductNotFoundException;
import com.api.productos.models.ProductModel;
import com.api.productos.repositories.IProductRepository;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Inject
    private IProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel getProductById(Long id) throws ProductNotFoundException {
        Optional<ProductModel> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw new ProductNotFoundException("Producto no encontrado");
        }
        throw new ProductNotFoundException("Producto no encontrado");
    }

    public ProductModel updateProduct(Long id, ProductModel updatedProduct) {
        Optional<ProductModel> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            ProductModel product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
