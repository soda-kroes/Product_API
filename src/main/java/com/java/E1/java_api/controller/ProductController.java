package com.java.E1.java_api.controller;

import com.java.E1.java_api.dto.request.ProductRequestDTO;
import com.java.E1.java_api.dto.response.StatusResponseDTO;
import com.java.E1.java_api.entitiy.Product;
import com.java.E1.java_api.exception.NotFoundException;
import com.java.E1.java_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create( @RequestBody ProductRequestDTO productRequestDTO) throws Exception {
        Map<String, Object> response = new HashMap<>();
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        Product data = productService.addProduct(productRequestDTO);
        if (data != null) {
            statusResponseDTO.setErrorCode(200);
            statusResponseDTO.setErrorMessage("Product created successfully");
            response.put("data", data);
        } else {
            statusResponseDTO.setErrorCode(204); // No Content
            statusResponseDTO.setErrorMessage("Your Content Unexceptionable!");
            response.put("data", null);
        }
        response.put("status", statusResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-all")
    public ResponseEntity<?> getAllLocations() {
        Map<String,Object> response = new HashMap<>();
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        List<Product> data = productService.getAllProducts();
        if (data.isEmpty()){
            statusResponseDTO.setErrorCode(204);
            statusResponseDTO.setErrorMessage("Product List is empty");
            response.put("data", null);
        }else{
            statusResponseDTO.setErrorCode(200);
            statusResponseDTO.setErrorMessage("Product list retrieved successfully");
            response.put("data", data);
        }
        response.put("status", statusResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable(name = "id") Long productId) throws NotFoundException {
        Map<String,Object> response = new HashMap<>();
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        Product data = productService.getProductById(productId);
        if (data==null){
            statusResponseDTO.setErrorCode(204);
            statusResponseDTO.setErrorMessage("Product is empty");
            response.put("data", null);
        }else{
            statusResponseDTO.setErrorCode(200);
            statusResponseDTO.setErrorMessage("Product retrieved successfully");
            response.put("data", data);
        }
        response.put("status", statusResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateLoan(@PathVariable(name = "id") Long productId, @RequestBody ProductRequestDTO productRequestDTO)throws Exception {
        Map<String, Object> response = new HashMap<>();
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        Product data = productService.updateProduct(productId, productRequestDTO);
        if (data != null) {
            statusResponseDTO.setErrorCode(200);
            statusResponseDTO.setErrorMessage("Product updated successfully");
            response.put("data", data);
        }else{
            statusResponseDTO.setErrorCode(204);
            statusResponseDTO.setErrorMessage("Your Content Unexceptionable!");
            response.put("data", null);
        }
        response.put("status", statusResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable(name = "id") Long productId) throws Exception {
        Map<String, Object> response = new HashMap<>();
        StatusResponseDTO statusResponseDTO = new StatusResponseDTO();
        Boolean isDelete = productService.deleteProduct(productId);
        if (isDelete) {
            statusResponseDTO.setErrorCode(200);
            statusResponseDTO.setErrorMessage("Product deleted successfully");
        }
        response.put("status", statusResponseDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
