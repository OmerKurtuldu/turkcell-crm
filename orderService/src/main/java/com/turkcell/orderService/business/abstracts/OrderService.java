package com.turkcell.orderService.business.abstracts;

import com.turkcell.orderService.business.dtos.request.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
