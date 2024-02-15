package com.teksen.ordermanagementsystem.controller;

import com.teksen.ordermanagementsystem.dto.ProductDTO;
import com.teksen.ordermanagementsystem.dto.mapper.ProductDTOMapper;
import com.teksen.ordermanagementsystem.model.Product;
import com.teksen.ordermanagementsystem.response.ResponseHandler;
import com.teksen.ordermanagementsystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductDTOMapper productDTOMapper;

    public ProductController(ProductService productService, ProductDTOMapper productDTOMapper) {
        this.productService = productService;
        this.productDTOMapper = productDTOMapper;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.OK,
                productService.getAllProducts().stream()
                        .map(productDTOMapper)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable Long productId){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.OK,
                productDTOMapper.apply(productService.getProductById(productId))
        );
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.OK,
                productDTOMapper.apply(productService.createProduct(product))
        );
    }

    @PutMapping("{productId}")
    public ResponseEntity<Object> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product
    ){
        return ResponseHandler.responseBuilder(
                "",
                HttpStatus.OK,
                productDTOMapper.apply(productService.updateProduct(productId, product))
        );
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<Object> deleteProduct(
            @PathVariable Long productId
    ){
        if (Boolean.TRUE.equals(productService.deleteProductById(productId))) {
            return ResponseHandler.responseBuilder(
                    "Product with ID " + productId + " deleted successfully",
                    HttpStatus.OK,
                    null
            );
        } else {
            return ResponseHandler.responseBuilder(
                    "Product with ID " + productId + " not found",
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
    }
}
