package com.scope.project.model;

import com.scope.project.entity.OrderADT;
import com.scope.project.entity.OrderLine;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Data
public class ArrayListDB {

    HashMap<OrderADT, List<OrderLine>> db = new HashMap<>();
    List<OrderLine> orderLines = new ArrayList<>();
    int olctr = 1;
    List<OrderADT> orderADTS = new ArrayList<>();
    int octr=1;

    public ArrayListDB(){
        orderADTS.add(new OrderADT(octr++,"Abhishek","Karol Bagh,Delhi","Chinchwad, Pune","Pay_on_delivery"));
        orderADTS.add(new OrderADT(octr++,"Ravi","Chembur ,Mumbai","Kalewadi ,Pune","Debit_Card"));
        orderLines.add((new OrderLine(olctr++,"Jeans Pant",1,2000)));
        orderLines.add((new OrderLine(olctr++,"Shirt",1,1200)));
        orderLines.add((new OrderLine(olctr++,"Trouser",1,2500)));
        orderLines.add((new OrderLine(olctr++,"Shoes",1,1000)));
    }

}
