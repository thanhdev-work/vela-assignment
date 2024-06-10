package com.example.velaassignment.services;

import com.example.velaassignment.constants.CurrencyEnum;
import com.example.velaassignment.entity.Product;
import com.example.velaassignment.exception.AppException;
import com.example.velaassignment.exception.ExceptionEnum;
import com.example.velaassignment.payload.req.AddProductRequest;
import com.example.velaassignment.payload.req.UpdateProductRequest;
import com.example.velaassignment.payload.res.ProductResponse;
import com.example.velaassignment.repository.ProductRepository;
import com.example.velaassignment.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream().map(e -> new ProductResponse(e.getId(), e.getName(), e.getQuantity(), e.getPrice(), e.getCurrencyEnum(),
                e.getDetail(), DateTimeUtils.format(e.getCreatedDate()), DateTimeUtils.format(e.getModifiedDate()))).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Optional<Product> var2 = productRepository.findById(id);

        if (var2.isPresent()) {
            return var2.map(e -> new ProductResponse(e.getId(), e.getName(), e.getQuantity(), e.getPrice(), e.getCurrencyEnum(),
                    e.getDetail(), DateTimeUtils.format(e.getCreatedDate()), DateTimeUtils.format(e.getModifiedDate()))).get();
        } else {
            throw new AppException(ExceptionEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<ProductResponse> getProductByDetail(String detail) {
        return productRepository.findAllByDetailContaining(detail)
                .stream()
                .map(e -> new ProductResponse(e.getId(), e.getName(), e.getQuantity(), e.getPrice(), e.getCurrencyEnum(),
                        e.getDetail(), DateTimeUtils.format(e.getCreatedDate()), DateTimeUtils.format(e.getModifiedDate())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackOn = AppException.class)
    public void updateQuantity(Long id, int quantity) {
        Optional<Product> var2 = productRepository.findById(id);
        if (var2.isPresent()) {
            Product product = var2.get();
            int newQuantity = product.getQuantity() - quantity;
            if (product.getQuantity() == 0 || newQuantity < 0)
                throw new AppException(ExceptionEnum.PRODUCT_NOT_ENOUGH_QUANTITY);
            product.setQuantity(newQuantity);
            productRepository.save(product);
        } else {
            throw new AppException(ExceptionEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public Product addProduct(AddProductRequest request) {
        Product var1 = new Product(null, request.getQuantity(), request.getName(), request.getPrice(), CurrencyEnum.VND,
                request.getDetail(), OffsetDateTime.now(), null);
        return productRepository.save(var1);
    }

    @Override
    public Product updateProduct(UpdateProductRequest request) {
        Optional<Product> var2 = productRepository.findById(request.getId());

        if (var2.isPresent()) {
            Product product = var2.get();
            product.setQuantity(request.getQuantity());
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setCurrencyEnum(CurrencyEnum.VND);
            product.setDetail(request.getDetail());
            product.setModifiedDate(OffsetDateTime.now());

            return productRepository.save(product);
        } else {
            throw new AppException(ExceptionEnum.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public String deleteProduct(List<Long> id) {
        if (id.isEmpty()) throw new AppException(ExceptionEnum.PRODUCT_NOT_ITEM_DELETE);
        String var1 = "";
        try {
            for (Long var2 : id) {
                Optional<Product> var3 = productRepository.findById(var2);
                if (!var3.isPresent()) throw new AppException("" + var2);
                productRepository.deleteById(var3.get().getId());

            }
        } catch (AppException e) {
            var1 = String.join(var1, e.getMessage());
        }
        if (var1.isEmpty()) {
            return "Success";
        }
        return "Delete fail for id: " + var1;
    }


}
