package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Musician;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository mockProductRepository;

    private ProductService productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productServiceUnderTest = new ProductService(mockProductRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setType("type");
        product.setGenre("genre");
        product.setPrice(0.0);
        final List<Product> expectedResult = List.of(product);

        // Configure ProductRepository.findAll(...).
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setType("type");
        product1.setGenre("genre");
        product1.setPrice(0.0);
        final List<Product> products = List.of(product1);
        when(mockProductRepository.findAll()).thenReturn(products);

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_ProductRepositoryReturnsNoItems() {
        // Setup
        when(mockProductRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Product> result = productServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindOne() {
        // Setup
        final Product expectedResult = new Product();
        expectedResult.setId(0);
        expectedResult.setName("name");
        expectedResult.setType("type");
        expectedResult.setGenre("genre");
        expectedResult.setPrice(0.0);

        // Configure ProductRepository.findById(...).
        final Product product1 = new Product();
        product1.setId(0);
        product1.setName("name");
        product1.setType("type");
        product1.setGenre("genre");
        product1.setPrice(0.0);
        final Optional<Product> product = Optional.of(product1);
        when(mockProductRepository.findById(0)).thenReturn(product);

        // Run the test
        final Product result = productServiceUnderTest.findOne(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindOne_ProductRepositoryReturnsAbsent() {
        // Setup
        when(mockProductRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Product result = productServiceUnderTest.findOne(0);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testSave() {
        // Setup
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setType("type");
        product.setGenre("genre");
        product.setPrice(0.0);

        // Run the test
        productServiceUnderTest.save(product);

        // Verify the results
        // Confirm ProductRepository.save(...).
        final Product entity = new Product();
        entity.setId(0);
        entity.setName("name");
        entity.setType("type");
        entity.setGenre("genre");
        entity.setPrice(0.0);
        verify(mockProductRepository).save(entity);
    }

    @Test
    void testUpdate() {
        // Setup
        final Product updatedProduct = new Product();
        updatedProduct.setId(0);
        updatedProduct.setName("name");
        updatedProduct.setType("type");
        updatedProduct.setGenre("genre");
        updatedProduct.setPrice(0.0);

        // Run the test
        productServiceUnderTest.update(0, updatedProduct);

        // Verify the results
        // Confirm ProductRepository.save(...).
        final Product entity = new Product();
        entity.setId(0);
        entity.setName("name");
        entity.setType("type");
        entity.setGenre("genre");
        entity.setPrice(0.0);
        verify(mockProductRepository).save(entity);
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        productServiceUnderTest.delete(0);

        // Verify the results
        verify(mockProductRepository).deleteById(0);
    }

    @Test
    void testGetProductByAllFields() {
        // Setup
        final Musician musician = new Musician("name", "members");
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setType("type");
        product.setGenre("genre");
        product.setPrice(0.0);
        final Optional<Product> expectedResult = Optional.of(product);

        // Configure ProductRepository.findByNameAndTypeAndGenreAndYearAndPriceAndDescription(...).
        final Product product2 = new Product();
        product2.setId(0);
        product2.setName("name");
        product2.setType("type");
        product2.setGenre("genre");
        product2.setPrice(0.0);
        final Optional<Product> product1 = Optional.of(product2);
        when(mockProductRepository.findByNameAndTypeAndGenreAndYearAndPriceAndDescription("name", "type", "genre", 2020,
                0.0, "description")).thenReturn(product1);

        // Run the test
        final Optional<Product> result = productServiceUnderTest.getProductByAllFields("name", "type", "genre", 2020,
                0.0, "description", musician);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetProductByAllFields_ProductRepositoryReturnsAbsent() {
        // Setup
        final Musician musician = new Musician("name", "members");
        when(mockProductRepository.findByNameAndTypeAndGenreAndYearAndPriceAndDescription("name", "type", "genre", 2020,
                0.0, "description")).thenReturn(Optional.empty());

        // Run the test
        final Optional<Product> result = productServiceUnderTest.getProductByAllFields("name", "type", "genre", 2020,
                0.0, "description", musician);

        // Verify the results
        assertThat(result).isEmpty();
    }
}
