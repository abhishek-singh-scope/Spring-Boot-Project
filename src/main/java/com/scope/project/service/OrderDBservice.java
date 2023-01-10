package com.scope.project.service;

import com.scope.project.entity.OrderADT;
import com.scope.project.exception.customException.IdNotFoundException;
import com.scope.project.model.ArrayListDB;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDBservice {

    ArrayListDB dbmodel = new ArrayListDB();

    public List<OrderADT> findAll() {
        return dbmodel.getOrderADTS();
    }


    public OrderADT findById(int theId) throws IdNotFoundException {

        for(OrderADT oadt:dbmodel.getOrderADTS())
        {
            if(oadt.getOid()==theId){
                return oadt;
            }
        }

        throw new IdNotFoundException("No such ID present!");
    }


    public OrderADT save(OrderADT theOrder) {

        for(OrderADT odt: dbmodel.getOrderADTS())
        {
            if(odt.getOid()==theOrder.getOid())
            {
                dbmodel.getOrderADTS().remove(odt);
            }
        }

        OrderADT newOrder = theOrder;
        dbmodel.getOrderADTS().add(newOrder);
        return newOrder;
    }


    public String deleteById(int theId) throws IdNotFoundException {

        for(OrderADT oadt:dbmodel.getOrderADTS())
        {
            if(oadt.getOid()==theId){
                dbmodel.getOrderADTS().remove(oadt);
                return "Order data was deleted!";
            }
        }

        throw new IdNotFoundException("No such ID exists");
    }
}
