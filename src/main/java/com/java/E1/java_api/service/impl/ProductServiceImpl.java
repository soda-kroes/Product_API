package com.java.E1.java_api.service.impl;

import com.java.E1.java_api.dto.request.ProductRequestDTO;
import com.java.E1.java_api.entitiy.Product;
import com.java.E1.java_api.exception.NotFoundException;
import com.java.E1.java_api.repository.ProductRepository;
import com.java.E1.java_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product addProduct(ProductRequestDTO productRequestDTO) throws Exception {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPricePerUnit(productRequestDTO.getPricePerUnit());
        product.setActiveForSell(productRequestDTO.isActiveForSell());
        try {
            return productRepository.save(product);
        }catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id "+id+" not found."));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id,ProductRequestDTO productRequestDTO) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found."));
        product.setName(productRequestDTO.getName());
        product.setPricePerUnit(productRequestDTO.getPricePerUnit());
        product.setActiveForSell(productRequestDTO.isActiveForSell());
        try {
            return productRepository.save(product);
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @Override
    public Boolean deleteProduct(Long id) throws NotFoundException {
        boolean find = false;
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id " + id + " not found."));
        if (Objects.equals(product.getId(),id)){
            productRepository.delete(product);
            return true;
        }
        return find;
    }
}
