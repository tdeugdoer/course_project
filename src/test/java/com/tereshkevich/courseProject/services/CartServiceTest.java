package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import com.tereshkevich.courseProject.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private OrdersRepository mockOrdersRepository;
    @Mock
    private ProductRepository mockProductRepository;

    private CartService cartServiceUnderTest;

    @BeforeEach
    void setUp() {
        cartServiceUnderTest = new CartService(mockOrdersRepository, mockProductRepository);
    }

    @Test
    void testFindOrder() {
        // Setup
        final Person person = new Person("login", "password", "role");

        // Configure OrdersRepository.findByCompletedAndPerson_Id(...).
        final Orders orders1 = new Orders();
        orders1.setId(0);
        orders1.setCompleted(false);
        orders1.setPrice(0.0);
        final Product product = new Product();
        product.setPrice(0.0);
        orders1.setProducts(List.of(product));
        final Optional<Orders> orders = Optional.of(orders1);
        when(mockOrdersRepository.findByCompletedAndPerson_Id(false, 0)).thenReturn(orders);

        // Run the test
        final Orders result = cartServiceUnderTest.findOrder(person);

        // Verify the results
    }

    @Test
    void testFindOrder_OrdersRepositoryReturnsAbsent() {
        // Setup
        final Person person = new Person("login", "password", "role");
        when(mockOrdersRepository.findByCompletedAndPerson_Id(false, 0)).thenReturn(Optional.empty());

        // Run the test
        final Orders result = cartServiceUnderTest.findOrder(person);

        // Verify the results
    }

    @Test
    void testDeleteProduct_OrdersRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockOrdersRepository.findById(0)).thenReturn(Optional.empty());

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
        cartServiceUnderTest.deleteProduct(0, 0);

        // Verify the results
    }

    @Test
    void testDeleteProduct_ProductRepositoryReturnsAbsent() {
        // Setup
        // Configure OrdersRepository.findById(...).
        final Orders orders1 = new Orders();
        orders1.setId(0);
        orders1.setCompleted(false);
        orders1.setPrice(0.0);
        final Product product = new Product();
        product.setPrice(0.0);
        orders1.setProducts(List.of(product));
        final Optional<Orders> orders = Optional.of(orders1);
        when(mockOrdersRepository.findById(0)).thenReturn(orders);

        when(mockProductRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        cartServiceUnderTest.deleteProduct(0, 0);

        // Verify the results
    }

    @Test
    void testCompleteOrder() {
        // Setup
        // Configure OrdersRepository.findById(...).
        final Orders orders1 = new Orders();
        orders1.setId(0);
        orders1.setCompleted(false);
        orders1.setPrice(0.0);
        final Product product = new Product();
        product.setPrice(0.0);
        orders1.setProducts(List.of(product));
        final Optional<Orders> orders = Optional.of(orders1);
        when(mockOrdersRepository.findById(0)).thenReturn(orders);

        // Run the test
        cartServiceUnderTest.completeOrder(0);

        // Verify the results
        verify(mockOrdersRepository).save(any(Orders.class));
    }

    @Test
    void testCompleteOrder_OrdersRepositoryFindByIdReturnsAbsent() {
        // Setup
        when(mockOrdersRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        cartServiceUnderTest.completeOrder(0);

        // Verify the results
    }
}
