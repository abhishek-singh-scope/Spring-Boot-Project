package com.scope.project.repository;

import com.scope.project.entity.OrderADT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderADT,Integer> {

    //InBuilt Query
    OrderADT findByOid(int oid);
    List<OrderADT> findByCustomerIgnoreCase(String customer);
    List<OrderADT> findByPlacedAtGreaterThan(Time time);
    List<OrderADT> findByPlacedOnBetween(Date d1, Date d2);

    //Named Query
    @Query("Select o from OrderADT o where o.grand_total = (Select MAX(grand_total) from OrderADT)")
    OrderADT findByMaxPriced();
    @Query("Select o.customer,SUM(o.grand_total) from OrderADT o group by o.customer having o.customer=?1")
    OrderADT findTotalCostToCustomer(String customer);

    //Native Query
    @Query(nativeQuery = true,value = "Select customer,grand_total from OrderADT group by " +
            "payment_method having payment_method=?1")
    List<OrderADT> collectByPaymentMethod(String method);
    @Query(nativeQuery = true,value = "Delete from OrderADT where customer like %?1%")
    OrderADT deleteByCustomerName(String customer);

    //Jpa-named-Query
    @Query(nativeQuery = true)
    List<OrderADT> getOrdersByLocation(String location);
}
