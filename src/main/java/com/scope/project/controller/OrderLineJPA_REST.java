package com.scope.project.controller;

import com.scope.project.entity.OrderADT;
import com.scope.project.entity.OrderLine;
import com.scope.project.repository.OrderLineRepository;
import com.scope.project.repository.OrderRepository;
import jakarta.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderLineJPA_REST {

    @Autowired
    OrderLineRepository orderLineRepository;

    @Autowired
    OrderRepository orderRepository;

    Logger logger = Logger.getLogger(OrdersArrayDB_REST.class);

    @GetMapping("/orderlines")
    public List<OrderLine> getOrderlines(){
        return  orderLineRepository.findAll();
    }

    @GetMapping("/orderlines/{id}")
    public OrderLine oneOrderline(@PathVariable int id){
        return orderLineRepository.findById(id);
    }

    @PutMapping("/orderlines/{id}")
    public OrderLine updateOL(@PathVariable int id,@RequestBody @Valid OrderLine ol){

        OrderLine upOL = new OrderLine(ol);
        upOL.setItem_id(id);
        logger.info("Updated OrderLine\n"+upOL);
        return orderLineRepository.save(upOL);
    }
    @PutMapping("/orderlines/{id}/addOrder/{oid}")
    public OrderLine updateOL(@PathVariable int id,@PathVariable int oid){

        OrderADT addord = orderRepository.findByOid(oid);
        OrderLine updOL = orderLineRepository.findById(id);
        double addTotal = addord.getGrand_total() + updOL.getTotal();
        addord.setGrand_total(addTotal);
        updOL.setOrderid(addord);
        logger.info("Updated Order\n"+addord);
        orderRepository.save(addord);
        logger.info("Updated OrderLine\n"+updOL);
        return orderLineRepository.save(updOL);
    }

    @PostMapping("/orderlines")
    public OrderLine saveOL(@RequestBody @Valid OrderLine ol){
        OrderLine upOL = new OrderLine(ol);
        logger.info("Added OrdeLine\n"+upOL);
        return orderLineRepository.save(upOL);
    }

    @DeleteMapping("/orderlines/{id}")
    public String delOL(@PathVariable int id){
        logger.info("Deleted Orderline/n"+orderLineRepository.findById(id));
        orderLineRepository.deleteById(id);
        return "Deleted OrderLine!";
    }

    @GetMapping("/orderlines/name/{name}")
    public List<OrderLine> findDistinctByItemContains(@PathVariable String item){
        return orderLineRepository.findDistinctByItemContains(item);
    }

    @GetMapping("/orderlines/price/{price}")
    public List<OrderLine> findByPriceLessThan(@PathVariable int price){
        return orderLineRepository.findByPriceLessThan(price);
    }

    @GetMapping("/orderlines/qty/{val}")
    public List<OrderLine> findByQuantityGreaterThan(@PathVariable int qty){
        return orderLineRepository.findByQuantityGreaterThan(qty);
    }

    @GetMapping("/orderlines/total/{val}")
    public List<OrderLine> findByMoreThanTotalPrice(@PathVariable int total){
        return orderLineRepository.findByMoreThanTotalPrice(total);
    }

    @GetMapping("/orderlines/oid/{id}")
    public List<OrderLine> findByOrderIDvalue(@PathVariable int id){
        return orderLineRepository.findByOrderIDvalue(id);
    }

    @GetMapping("/orderlines/count/{oid}")
    public OrderLine countTotalItemsByOrderID(@PathVariable int oid){
        return orderLineRepository.countTotalItemsByOrderID(oid);
    }

    @GetMapping("/orderlines/count")
    public List<OrderLine> countTotalItemsPerOrderID(){
        return orderLineRepository.countTotalItemsPerOrderID();
    }

    //JPA-named-props
    @GetMapping("/orderlines/totalamt/{oid}")
    public OrderLine getTotalAmountByOrderID(@PathVariable int oid){
        return orderLineRepository.getTotalAmountByOrderID(oid);
    }

    @GetMapping("/orderlines/totalamt")
    public List<OrderLine> getTotalPerOrderID(){
        return orderLineRepository.getTotalPerOrderID();
    }
}
