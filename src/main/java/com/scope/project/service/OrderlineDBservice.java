package com.scope.project.service;

import com.scope.project.entity.OrderLine;
import com.scope.project.exception.customException.IdNotFoundException;
import com.scope.project.model.ArrayListDB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderlineDBservice {

    ArrayListDB dbmodel = new ArrayListDB();

    public List<OrderLine> findAll() {
        return dbmodel.getOrderLines();
    }


    public OrderLine findById(int theId) throws IdNotFoundException {

        for(OrderLine odl:dbmodel.getOrderLines())
        {
            if(odl.getItem_id()==theId){
                return odl;
            }
        }
        throw new IdNotFoundException("No such ID present!");
    }

    public OrderLine save(OrderLine theOrderLine) {

        for(OrderLine odl: dbmodel.getOrderLines())
        {
            if(odl.getItem_id() == theOrderLine.getItem_id())
            {
                dbmodel.getOrderLines().remove(odl);
            }
        }

        OrderLine newODL = new OrderLine(theOrderLine);
        dbmodel.getOrderLines().add(newODL);
        return newODL;
    }

    public String deleteById(int theId) throws IdNotFoundException {

        for(OrderLine odl:dbmodel.getOrderLines())
        {
            if(odl.getItem_id()==theId){
                dbmodel.getOrderLines().remove(odl);
                return "OrderLine Deleted!";
            }
        }
        throw new IdNotFoundException("No such ID present!");
    }
}
