package com.tereshkevich.courseProject.controllers;

import com.tereshkevich.courseProject.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService orderService) {
        this.ordersService = orderService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("orders", ordersService.findCompletedOrders());
        return "admin/orders/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/admin/orders";
    }
}
