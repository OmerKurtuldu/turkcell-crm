package com.turkcell.orderService.business.concretes;


import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import com.turkcell.orderService.api.clients.AccountServiceClient;
import com.turkcell.orderService.api.clients.BasketServiceClient;
import com.turkcell.orderService.api.clients.CatalogServiceClient;
import com.turkcell.orderService.api.clients.CustomerServiceClient;
import com.turkcell.orderService.business.abstracts.OrderService;
import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.orderService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.orderService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.orderService.business.dtos.response.get.GetProductResponse;
import com.turkcell.orderService.dataAccess.OrderRepository;
import com.turkcell.orderService.entities.Order;
import com.turkcell.orderService.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderManager implements OrderService {
    private final ModelMapperService modelMapperService;
    private final OrderRepository orderRepository;
    private final BasketServiceClient basketServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final AccountServiceClient accountServiceClient;
    private final CustomerServiceClient customerServiceClient;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {

        GetBasketResponse getBasketResponse = basketServiceClient.basketGetById(createOrderRequest.getBasketId());
        Order order = new Order();
        order.setOrderItems(getBasketResponse.getOrderItems());
        order.setBasketId(getBasketResponse.getId());
        order.setTotalPrice(getBasketResponse.getTotalPrice());
        order.setId(0);
        GetAccountResponse getAccountResponse = accountServiceClient.accountGetById(Integer.valueOf(getBasketResponse.getAccountId()));

        List <GetProductResponse> getProductResponses = new ArrayList<>();
        List<GetAddressResponse> getAddressResponses = new ArrayList<>();

        OrderItem orderItem = new OrderItem();
        for(var basket : getBasketResponse.getOrderItems()){
            orderItem.setProductName(basket.getProductName());
            orderItem.setProductId(basket.getProductId());
            orderItem.setPrice(basket.getPrice());
            order.getOrderItems().add(orderItem);


            GetProductResponse getProductResponse = catalogServiceClient.productGetById(orderItem.getProductId());
            getProductResponses.add(getProductResponse);

        }
        for(int addressId : getAccountResponse.getAddressId()){
            GetAddressResponse getAddressResponse = customerServiceClient.addressGetById(addressId);
            getAddressResponses.add(getAddressResponse);

        }
        orderRepository.save(order);
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setTotalPrice(order.getTotalPrice());
        createOrderResponse.setGetProductResponse(getProductResponses);
        createOrderResponse.setGetAccountResponse(getAccountResponse);
        createOrderResponse.setGetAddressResponse(getAddressResponses);
        return createOrderResponse;

    }

}


