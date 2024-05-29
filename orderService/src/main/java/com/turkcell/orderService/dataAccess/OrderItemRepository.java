package com.turkcell.orderService.dataAccess;

import com.turkcell.orderService.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
