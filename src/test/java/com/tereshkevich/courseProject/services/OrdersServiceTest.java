package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    @Mock
    private OrdersRepository mockOrdersRepository;

    private OrdersService ordersServiceUnderTest;

    @BeforeEach
    void setUp() {
        ordersServiceUnderTest = new OrdersService(mockOrdersRepository);
    }

    @Test
    void testFindAll() {
        // Setup
        // Configure OrdersRepository.findAll(...).
        final Orders orders1 = new Orders();
        orders1.setId(0);
        orders1.setCompleted(false);
        orders1.setPrice(0.0);
        final Person person = new Person();
        person.setId(0);
        orders1.setPerson(person);
        final List<Orders> orders = List.of(orders1);
        when(mockOrdersRepository.findAll()).thenReturn(orders);

        // Run the test
        final List<Orders> result = ordersServiceUnderTest.findAll();

        // Verify the results
    }

    @Test
    void testFindAll_OrdersRepositoryReturnsNoItems() {
        // Setup
        when(mockOrdersRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Orders> result = ordersServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindCompletedOrders() {
        // Setup
        // Configure OrdersRepository.findOrdersByCompleted(...).
        final Orders orders1 = new Orders();
        orders1.setId(0);
        orders1.setCompleted(false);
        orders1.setPrice(0.0);
        final Person person = new Person();
        person.setId(0);
        orders1.setPerson(person);
        final List<Orders> orders = List.of(orders1);
        when(mockOrdersRepository.findOrdersByCompleted(true)).thenReturn(orders);

        // Run the test
        final List<Orders> result = ordersServiceUnderTest.findCompletedOrders();

        // Verify the results
    }

    @Test
    void testFindCompletedOrders_OrdersRepositoryReturnsNoItems() {
        // Setup
        when(mockOrdersRepository.findOrdersByCompleted(true)).thenReturn(Collections.emptyList());

        // Run the test
        final List<Orders> result = ordersServiceUnderTest.findCompletedOrders();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testSave() {
        // Setup
        final Orders order = new Orders();
        order.setId(0);
        order.setCompleted(false);
        order.setPrice(0.0);
        final Person person = new Person();
        person.setId(0);
        order.setPerson(person);

        // Run the test
        ordersServiceUnderTest.save(order);

        // Verify the results
        verify(mockOrdersRepository).save(any(Orders.class));
    }

    @Test
    void testUpdate() {
        // Setup
        final Orders updatedOrder = new Orders();
        updatedOrder.setId(0);
        updatedOrder.setCompleted(false);
        updatedOrder.setPrice(0.0);
        final Person person = new Person();
        person.setId(0);
        updatedOrder.setPerson(person);

        // Run the test
        ordersServiceUnderTest.update(0, updatedOrder);

        // Verify the results
        verify(mockOrdersRepository).save(any(Orders.class));
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        ordersServiceUnderTest.delete(0);

        // Verify the results
        verify(mockOrdersRepository).deleteById(0);
    }
}
