package com.teksen.ordermanagementsystem.dto.mapper;

import com.teksen.ordermanagementsystem.dto.ProductDTO;
import com.teksen.ordermanagementsystem.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getProductName(),
                product.getPrice()
        );
    }
}
