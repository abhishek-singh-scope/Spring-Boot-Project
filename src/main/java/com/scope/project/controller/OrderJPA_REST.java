package com.scope.project.controller;

import com.scope.project.entity.OrderADT;
import com.scope.project.repository.OrderRepository;
import jakarta.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderJPA_REST {

    @Autowired
    OrderRepository orderRepository;

    Logger logger = Logger.getLogger(OrdersArrayDB_REST.class);

    @GetMapping("/orders")
    public List<OrderADT> getOrders(){
        return  orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderADT> oneOrder(@PathVariable int id){
        return orderRepository.findById(id);
    }

    @PutMapping("/orders/{id}")
    public OrderADT updateOrd(@PathVariable int id,@RequestBody @Valid OrderADT ord){
        OrderADT upOrd = new OrderADT(ord);
        upOrd.setOid(id);
        logger.info("Updated Order\n"+upOrd);
        return orderRepository.save(upOrd);
    }

    @PostMapping("/orders")
    public OrderADT saveOrd(@RequestBody @Valid OrderADT ord){
        OrderADT upOrd = new OrderADT(ord);
        logger.info("Added Order\n"+upOrd);
        return orderRepository.save(upOrd);
    }

    @DeleteMapping("/orders/{id}")
    public String delOrd(@PathVariable int id){
        logger.info("Deleted Order/n"+orderRepository.findById(id));
        orderRepository.deleteById(id);
        return "Deleted Order!";
    }

    @GetMapping("/orders/{customer}")
    public List<OrderADT> findByCustomerIgnoreCase(@PathVariable String customer){
        return orderRepository.findByCustomerIgnoreCase(customer);
    }

    @GetMapping("/orders/time/{time}")
    public List<OrderADT> findByPlaced_atGreaterThan(@PathVariable Time time){
        return orderRepository.findByPlacedAtGreaterThan(time);
    }

    @GetMapping("/orders/date/{d1}/{d2}")
    public List<OrderADT> findByPlaced_onBetween(@PathVariable Date d1,@PathVariable Date d2){
        return orderRepository.findByPlacedOnBetween(d1,d2);
    }

    @GetMapping("/orders/maxpriced")
    public OrderADT findByMaxPriced(){
        return orderRepository.findByMaxPriced();
    }

    @GetMapping("/orders/total/{customer}")
    public OrderADT findTotalCostToCustomer(@PathVariable String customer){
        return orderRepository.findTotalCostToCustomer(customer);
    }

    @GetMapping("/orders/payment/{method}")
    public List<OrderADT> collectByPaymentMethod(@PathVariable String method){
        return orderRepository.collectByPaymentMethod(method);
    }

    @DeleteMapping("/orders/{customer}")
    public OrderADT deleteByCustomerName(@PathVariable String customer){
        return orderRepository.deleteByCustomerName(customer);
    }

    @GetMapping("/orders/location/{location}")
    public List<OrderADT> getOrdersByLocation(@PathVariable String location){
        return orderRepository.getOrdersByLocation(location);
    }
}
