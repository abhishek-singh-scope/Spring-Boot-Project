package com.scope.project.entity;

import com.scope.project.exception.validation.ValidatePayment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class OrderADT {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @NotBlank
    private String customer;
    @Column(name = "Time")
    @Temporal(TemporalType.TIME)
    private Time placedAt;


    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date placedOn;

    @NotBlank
    @Size(min = 20, max = 200)
    private String Shipping_address;

    @NotBlank
    @Size(min=20, max = 200)
    private String Billing_address;

    @NotNull
    @ValidatePayment
    private String payment_method;

    private double grand_total;

    @JsonIgnore
    @OneToMany(mappedBy = "orderid",cascade = CascadeType.ALL)
    @Transient
    private List<OrderLine> orderline = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderADT orderADT)) return false;
        return getOid() == orderADT.getOid() && Double.compare(orderADT.getGrand_total(), getGrand_total()) == 0 && getCustomer().equals(orderADT.getCustomer())
                && getPlacedAt().equals(orderADT.getPlacedAt()) && getPlacedOn().equals(orderADT.getPlacedOn())
                && getShipping_address().equals(orderADT.getShipping_address()) && getBilling_address().equals(orderADT.getBilling_address())
                && getPayment_method().equals(orderADT.getPayment_method()) && Objects.equals(getOrderline(), orderADT.getOrderline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOid(), getCustomer(), getPlacedAt(), getPlacedOn(), getShipping_address(), getBilling_address(), getPayment_method(), getGrand_total(), getOrderline());
    }

    public OrderADT(OrderADT orderADT) {

        this.customer = orderADT.getCustomer();
        this.placedAt = Time.valueOf(LocalTime.now());
        this.placedOn = Date.valueOf(LocalDate.now());
        Shipping_address = orderADT.getShipping_address();
        Billing_address = orderADT.getBilling_address();
        this.payment_method = orderADT.getPayment_method();
    }

    public OrderADT(int id,String customer, String ship, String bill, String method) {

        this.oid=id;
        this.customer = customer;
        this.placedAt = Time.valueOf(LocalTime.now());
        this.placedOn = Date.valueOf(LocalDate.now());
        Shipping_address = ship;
        Billing_address = bill;
        this.payment_method = method;
    }
}
