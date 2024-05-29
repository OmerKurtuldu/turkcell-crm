package com.turkcell.orderService.business.abstracts;

import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
