package com.turkcell.orderService.business.abstracts;

import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.GetOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    GetOrderResponse getOrderById(int orderId);

    List<GetOrderResponse> getAllOrders();

    void deleteOrder(int orderId);
}
