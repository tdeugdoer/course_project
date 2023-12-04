package com.tereshkevich.courseProject.util;

import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final ProductService productService;

    @Autowired
    public ProductValidator(ProductService productService) { this.productService = productService; }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Product product = (Product) o;

        if (productService.getProductByAllFields(product.getName(), product.getType(), product.getGenre(), product.getYear(), product.getPrice(), product.getDescription(), product.getMusician()).isPresent())
            errors.rejectValue("description", "", "Такой продукт уже имеется. Изменить значение какого-нибудь поля");
    }
}