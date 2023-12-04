package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public List<Orders> findCompletedOrders() { return ordersRepository.findOrdersByCompleted(true); }

    @Transactional
    public void save(Orders order) {
        ordersRepository.save(order);
    }

    @Transactional
    public void update(int id, Orders updatedOrder) {
        updatedOrder.setId(id);
        ordersRepository.save(updatedOrder);
    }

    @Transactional
    public void delete(int id) {
        ordersRepository.deleteById(id);
    }

}
