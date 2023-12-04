package com.tereshkevich.courseProject.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "completed")
    private boolean completed;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany
    @JoinTable(
            name = "Product_Order",
            joinColumns = @JoinColumn(name = "order_id") ,
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Orders() {}

    public Orders(boolean completed, double price, Person person, List<Product> products) {
        this.completed = completed;
        this.price = price;
        this.person = person;
        this.products = products;
    }

    public Orders(int id, boolean completed, double price, Person person, List<Product> products) {
        this.id = id;
        this.completed = completed;
        this.price = price;
        this.person = person;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", completed=" + completed +
                ", price=" + price +
                ", person=" + person +
                ", products=" + products +
                '}';
    }
}
