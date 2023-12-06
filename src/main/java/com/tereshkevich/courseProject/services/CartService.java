package com.tereshkevich.courseProject.services;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import com.tereshkevich.courseProject.repositories.OrdersRepository;
import com.tereshkevich.courseProject.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartService {
    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(OrdersRepository ordersRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Orders findOrder(Person person) {
        Optional<Orders> opt_order = ordersRepository.findByCompletedAndPerson_Id(false, person.getId());
        if(opt_order.isPresent()){
            return opt_order.get();
        }
        else return new Orders();
    }

    public void deleteProduct(int idProd, int idOrder) {
        Optional<Orders> optOrder = ordersRepository.findById(idOrder);
        Optional<Product> optProduct = productRepository.findById(idProd);
        if(optOrder.isPresent() && optProduct.isPresent()){
            Orders order = optOrder.get();
            Product product = optProduct.get();
            order.getProducts().remove(product);
            order.setPrice(order.getPrice() - product.getPrice());
            ordersRepository.save(order);
        }
    }

    public void completeOrder(int id) {
        Optional<Orders> optOrder = ordersRepository.findById(id);
        if(optOrder.isPresent()){
            Orders order = optOrder.get();
            order.setCompleted(true);
            ordersRepository.save(order);
        }
    }
}
