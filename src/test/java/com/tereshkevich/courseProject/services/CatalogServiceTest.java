package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
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
class CatalogServiceTest {

    @Mock
    private OrdersRepository mockOrdersRepository;

    private CatalogService catalogServiceUnderTest;

    @BeforeEach
    void setUp() {
        catalogServiceUnderTest = new CatalogService(mockOrdersRepository);
    }

    @Test
    void testAddProduct_OrdersRepositoryFindByCompletedAndPerson_IdReturnsAbsent() {
        // Setup
        final Product product = new Product();
        product.setId(0);
        product.setName("name");
        product.setType("type");
        product.setGenre("genre");
        product.setPrice(0.0);

        final Person person = new Person("login", "password", "role");
        when(mockOrdersRepository.findByCompletedAndPerson_Id(false, 0)).thenReturn(Optional.empty());

        // Run the test
        catalogServiceUnderTest.addProduct(product, person);

        // Verify the results
        verify(mockOrdersRepository).save(any(Orders.class));
    }
}
