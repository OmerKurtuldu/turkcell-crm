package com.turkcell.orderService.business.concretes;

import com.turkcell.orderService.business.abstracts.OrderService;
import com.turkcell.orderService.business.dtos.request.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.CreateOrderResponse;

public class OrderManager implements OrderService {
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        return null;
    }
}
