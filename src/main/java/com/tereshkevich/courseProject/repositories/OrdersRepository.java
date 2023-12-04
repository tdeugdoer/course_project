package com.tereshkevich.courseProject.repositories;

import com.tereshkevich.courseProject.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findOrdersByCompleted(boolean completed);
}
