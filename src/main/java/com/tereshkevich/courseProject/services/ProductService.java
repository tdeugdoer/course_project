package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Comment;
import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.CommentRepository;
import com.tereshkevich.courseProject.repositories.ProductRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.standard.PrinterURI;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findOne(int id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.orElse(null);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        productRepository.save(updatedProduct);
    }

    @Transactional
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductByAllFields(String name, String type, String genre, int year, double price, String description, Musician musician) {
        return productRepository.findByNameAndTypeAndGenreAndYearAndPriceAndDescription(name, type, genre, year, price, description);
    }
}
