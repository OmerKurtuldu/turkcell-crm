package com.turkcell.orderService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.corepackage.entities.BaseEntity;
import com.turkcell.orderService.business.dtos.response.get.GetOrderItemResponse;
import com.turkcell.orderService.util.OrderNumberGenerator;
import jakarta.persistence.*;
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
    private int accountId;
    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @PrePersist
    private void prePersist() {
        if (this.orderNumber == null || this.orderNumber.isEmpty()) {
            this.orderNumber = OrderNumberGenerator.generateOrderNumber();
        }
    }
}


