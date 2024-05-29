package com.turkcell.orderService.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.corepackage.entities.BaseEntity;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

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
    @JsonIgnore
    @ElementCollection(fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
