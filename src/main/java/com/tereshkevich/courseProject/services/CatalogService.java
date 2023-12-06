package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {
    public final OrdersRepository ordersRepository;

    @Autowired
    public CatalogService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Transactional
    public void addProduct(Product product, Person person) {
        Optional<Orders> opt_order = ordersRepository.findByCompletedAndPerson_Id(false, person.getId());
        if(opt_order.isPresent()){
            Orders order = opt_order.get();
            if(!order.getProducts().contains(product)) {
                order.setPrice(order.getPrice() + product.getPrice());
                order.getProducts().add(product);
                ordersRepository.save(order);
            }
        }
        else {
            ordersRepository.save(new Orders(false, product.getPrice(), person, List.of(product)));
        }
    }
}
