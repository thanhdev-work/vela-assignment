package com.example.velaassignment.repository;

import com.example.velaassignment.entity.Order;
import com.example.velaassignment.payload.res.GetOrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.id AS orderId, " +
            "o.customer_name AS customerName, " +
            "o.address AS address, " +
            "o.phone_number AS phone, " +
            "o.email AS email, " +
            "o.order_status AS orderStatus, " +
            "o.created_date AS createdDate, " +
            "o.modified_date AS modifiedDate, " +
            "p.* AS orderProducts " +
            "FROM orders o " +
            "JOIN order_detail op ON o.id = op.order_id " +
            "JOIN product p ON op.product_id = p.id " +
            "WHERE o.id = :orderId", nativeQuery = true)
    Optional<GetOrderResponse> getOrderById(@Param("orderId") Long orderId);
}
