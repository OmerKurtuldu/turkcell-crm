package com.turkcell.orderService.entities;

import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {

    private String orderNumber;
    private String basketId;
    private Double totalPrice;
    private List<OrderItem> orderItems;
}
