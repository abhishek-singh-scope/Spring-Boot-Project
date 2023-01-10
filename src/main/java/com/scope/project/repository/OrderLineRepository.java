package com.scope.project.repository;

import com.scope.project.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {

    //InBuilt Query
    List<OrderLine> findDistinctByItemContains(String item);
    List<OrderLine> findByPriceLessThan(int price);
    List<OrderLine> findByQuantityGreaterThan(int qty);
    OrderLine findById(int id);

    //Named Query
    @Query("Select ol from OrderLine ol where ol.total>=?1")
    List<OrderLine> findByMoreThanTotalPrice(int total);
    @Query("Select ol from OrderLine ol where ol.orderid=?1")
    List<OrderLine> findByOrderIDvalue(int id);

    //Native Query
    @Query(value = "Delete from OrderLine where item=?1",nativeQuery = true)
    List<OrderLine> deleteByItemName(String name);
    @Query(value = "Select count(*) from OrderLine where orderid=?1",nativeQuery = true)
    OrderLine countTotalItemsByOrderID(int oid);
    @Query(value = "Select count(*) from OrderLine group by orderid",nativeQuery = true)
    List<OrderLine> countTotalItemsPerOrderID();

    //Jpa-named-Query
    @Query(nativeQuery = true)
    OrderLine getTotalAmountByOrderID(int oid);
    @Query
    List<OrderLine> getTotalPerOrderID();
}
