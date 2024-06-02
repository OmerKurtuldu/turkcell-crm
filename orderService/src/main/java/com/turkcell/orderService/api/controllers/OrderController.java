package com.turkcell.orderService.api.controllers;


import com.turkcell.orderService.business.abstracts.OrderService;
import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.GetOrderResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orderservice/api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(createOrderRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetOrderResponse orderGetById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetOrderResponse> getAll() {
        return orderService.getAllOrders();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void orderDeleteById(@PathVariable int id) {
        orderService.deleteOrder(id);
    }
}
