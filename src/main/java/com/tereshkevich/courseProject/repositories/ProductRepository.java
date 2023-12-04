package com.tereshkevich.courseProject.repositories;

import com.tereshkevich.courseProject.models.Person;
import com.tereshkevich.courseProject.models.Product;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameAndTypeAndGenreAndYearAndPriceAndDescription(String name, String type, String genre, int year, double price, String description);

}
