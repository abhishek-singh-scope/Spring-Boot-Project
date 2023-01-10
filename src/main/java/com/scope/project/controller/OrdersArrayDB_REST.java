package com.scope.project.controller;

import com.scope.project.entity.OrderADT;
import com.scope.project.entity.OrderLine;
import com.scope.project.exception.customException.IdNotFoundException;
import com.scope.project.service.OrderDBservice;
import com.scope.project.service.OrderlineDBservice;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/db")
public class OrdersArrayDB_REST {

    @Autowired
    OrderlineDBservice orderlineDBservice;

    @Autowired
    OrderDBservice orderDBservice;

    Logger logger = Logger.getLogger(OrdersArrayDB_REST.class);

    @GetMapping("/orderlines")
    public List<OrderLine> getOrderlines(){
        return orderlineDBservice.findAll();
    }

    @GetMapping("/orders")
    public List<OrderADT> getOrders(){
        return orderDBservice.findAll();
    }

    @GetMapping("/orderlines/{id}")
    public OrderLine getOrderlinesById(@PathVariable int id) throws IdNotFoundException {
        return orderlineDBservice.findById(id);
    }

    @GetMapping("/orders/{id}")
    public OrderADT getOrdersById(@PathVariable int id) throws IdNotFoundException {
        return orderDBservice.findById(id);
    }

    @PutMapping("/orderlines/{id}")
    public OrderLine updateOrderline(@PathVariable int id, @RequestBody @Valid OrderLine ordline) throws IdNotFoundException {
        logger.info("Updating Orderline: "+orderlineDBservice.findById(id));
        ordline.setItem_id(id);
        return orderlineDBservice.save(ordline);
    }

    @PutMapping("/orders/{id}")
    public OrderADT updateOrder(@PathVariable int id, @RequestBody @Valid OrderADT ord) throws IdNotFoundException {
        logger.info("Updating Order: "+orderDBservice.findById(id));
        ord.setOid(id);
        return orderDBservice.save(ord);
    }

    @PostMapping("/orderlines")
    public OrderLine addOrderline(@RequestBody @Valid OrderLine odl){
        OrderLine orderLine = new OrderLine(odl);
        logger.info("Adding OrderLine: "+orderLine);
        return orderlineDBservice.save(orderLine);
    }

    @PostMapping("/orders")
    public OrderADT addOrder(@RequestBody @Valid OrderADT od){
        OrderADT odt = new OrderADT(od);
        logger.info("Adding Order: "+odt);
        return orderDBservice.save(odt);
    }

    @DeleteMapping("/orders/{id}")
    public String delOrder(@PathVariable int id) throws IdNotFoundException {
        logger.info("Deleted Order: "+orderDBservice.findById(id));
        return orderDBservice.deleteById(id);
    }

    @DeleteMapping("/orderlines/{id}")
    public String delOrderline(@PathVariable int id) throws IdNotFoundException {
        logger.info("Deleted OrderLine: "+orderlineDBservice.findById(id));
        return orderlineDBservice.deleteById(id);
    }

}
