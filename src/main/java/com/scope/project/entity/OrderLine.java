package com.scope.project.entity;

//import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scope.project.repository.OrderRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Data
@NoArgsConstructor
public class OrderLine {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int item_id;
    @NotNull(message = "Item name must be mentioned")
    private String item;
    @NotNull
    @Min(value = 1,message = "quantity must be atleast 1")
    private int quantity;
    @NotNull
    @Min(value = 99,message = "price value must be atleast 99")
    private double price;
    private double total;
    @ManyToOne
    @JoinColumn(name = "oid")
    private OrderADT orderid;

    public OrderLine(int id,String item, int quantity, double price) {

        this.item_id=id;
        this.item = item;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity*price;
    }

    public OrderLine(OrderLine odl){

        this.item = odl.getItem();
        this.quantity = odl.getQuantity();
        this.price = odl.getPrice();
        this.total = odl.getPrice()*odl.getQuantity();
    }
}
