package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.model.Product;
import com.teksen.ordermanagementsystem.repository.ProductRepository;
import com.teksen.ordermanagementsystem.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found!"));
    }

    @Override
    public Product createProduct(Product toCreateProduct) {
        return productRepository.save(toCreateProduct);
    }

    @Override
    public Product updateProduct(Long productId, Product toUpdateProduct) {
        Product product = this.getProductById(productId);

        product.setProductName(toUpdateProduct.getProductName());
        product.setPrice(toUpdateProduct.getPrice());

        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        return productRepository.deleteProductByProductId(productId).isPresent();
    }
}
