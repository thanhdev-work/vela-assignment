package com.example.velaassignment.controllers;

import com.example.velaassignment.helper.validator.ServiceValidate;
import com.example.velaassignment.payload.req.AddProductRequest;
import com.example.velaassignment.payload.req.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.velaassignment.services.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/emc/v1/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> products() {
        return ok(productService.getAllProduct());
    }

    @GetMapping("")
    @ServiceValidate
    public ResponseEntity<?> getProduct(@RequestParam(name = "id") @Min(value = 0, message = "40002") @NotNull(message = "40001") Long id, BindingResult bindingResult) {
        return ok(productService.getProduct(id));
    }

    @GetMapping("/find")
    public ResponseEntity<?> getProductByDetail(@RequestParam(name = "detail") String detail) {
        return ok(productService.getProductByDetail(detail));
    }

    @PostMapping("/add")
    @ServiceValidate
    public ResponseEntity<?> addProduct(@RequestBody @Valid AddProductRequest request, BindingResult bindingResult) {
        return ok(productService.addProduct(request));
    }

    @PutMapping("/update")
    @ServiceValidate
    public ResponseEntity<?> updateProduct(@RequestBody @Valid UpdateProductRequest request, BindingResult bindingResult) {
        return ok(productService.updateProduct(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam(name = "id") List<Long> id) {
        return ok(productService.deleteProduct(id));
    }

}
