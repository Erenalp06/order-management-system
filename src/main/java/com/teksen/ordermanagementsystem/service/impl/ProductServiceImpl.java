package com.teksen.ordermanagementsystem.service.impl;

import com.teksen.ordermanagementsystem.exception.custom.ProductNotFoundException;
import com.teksen.ordermanagementsystem.model.Product;
import com.teksen.ordermanagementsystem.repository.ProductRepository;
import com.teksen.ordermanagementsystem.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        logger.info("Getting all products");
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        logger.info("Getting product by id: {}", productId);
        return productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product with id: " + productId + " not found"));
    }

    @Override
    public Product createProduct(Product toCreateProduct) {
        try{
            return productRepository.save(toCreateProduct);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public Product updateProduct(Long productId, Product toUpdateProduct) {
        Product product = this.getProductById(productId);

        product.setProductName(toUpdateProduct.getProductName());
        product.setPrice(toUpdateProduct.getPrice());

        try{
            return productRepository.save(product);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred");
        }
    }

    @Override
    public Boolean deleteProductById(Long productId) {
        if(productRepository.existsById(productId)){
            try{
                productRepository.deleteById(productId);
                logger.info("Product with id: {} deleted", productId);
            } catch (DataAccessException ex) {
                throw new RuntimeException("An unexpected error occurred");
            }
            return true;
        }
        return false;
    }
}
