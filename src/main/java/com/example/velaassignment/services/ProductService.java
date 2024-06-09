package com.example.velaassignment.services;

import com.example.velaassignment.entity.Product;
import com.example.velaassignment.payload.req.AddProductRequest;
import com.example.velaassignment.payload.req.UpdateProductRequest;
import com.example.velaassignment.payload.res.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();

    ProductResponse getProduct(Long id);

    Product addProduct(AddProductRequest request);

    Product updateProduct(UpdateProductRequest request);

    String deleteProduct(List<Long> id);

    List<ProductResponse> getProductByDetail(String detail);
}
