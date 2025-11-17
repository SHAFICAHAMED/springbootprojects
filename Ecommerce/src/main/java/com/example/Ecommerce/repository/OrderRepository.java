package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUsername(@Param("name") String name);
    @Query("SELECT o FROM Order o WHERE o.user.name = :name ORDER BY o.orderDate DESC")
    List<Order> findByUserUsernameOrderByOrderDateDesc(String name);
    List<Order> findOrdersByUserIdOrderByOrderDateDesc(Long userId);

}
