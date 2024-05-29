package com.turkcell.orderService.dataAccess;

import com.turkcell.orderService.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
