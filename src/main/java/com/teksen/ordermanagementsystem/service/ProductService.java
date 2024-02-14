package com.teksen.ordermanagementsystem.service;

import com.teksen.ordermanagementsystem.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long productId);
    Product createProduct(Product toCreateProduct);
    Product updateProduct(Long productId, Product toUpdateProduct);
    Boolean deleteProductById(Long productId);
}
