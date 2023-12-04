package com.tereshkevich.courseProject.repositories;

import com.tereshkevich.courseProject.models.Orders;
import com.tereshkevich.courseProject.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findOrdersByCompleted(boolean completed);
    Optional<Orders> findByCompletedAndPerson_Id(boolean completed, int person_id);
}
