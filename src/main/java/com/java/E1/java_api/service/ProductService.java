package com.java.E1.java_api.service;

import com.java.E1.java_api.dto.request.ProductRequestDTO;
import com.java.E1.java_api.entitiy.Product;
import com.java.E1.java_api.exception.NotFoundException;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductRequestDTO productRequestDTO) throws Exception;
    Product getProductById(Long id) throws NotFoundException;
    List<Product> getAllProducts();
    Product updateProduct(Long id,ProductRequestDTO productRequestDTO) throws Exception;
    Boolean deleteProduct(Long id) throws NotFoundException;
}
