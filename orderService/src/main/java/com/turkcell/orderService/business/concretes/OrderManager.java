package com.turkcell.orderService.business.concretes;

import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import com.turkcell.orderService.api.clients.AccountServiceClient;
import com.turkcell.orderService.api.clients.BasketServiceClient;
import com.turkcell.orderService.api.clients.CatalogServiceClient;
import com.turkcell.orderService.api.clients.CustomerServiceClient;
import com.turkcell.orderService.business.abstracts.OrderService;
import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.*;
import com.turkcell.orderService.business.rules.OrderBusinessRules;
import com.turkcell.orderService.dataAccess.OrderRepository;
import com.turkcell.orderService.entities.Order;
import com.turkcell.orderService.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderManager implements OrderService {
    private final ModelMapperService modelMapperService;
    private final OrderRepository orderRepository;
    private final BasketServiceClient basketServiceClient;
    private final CatalogServiceClient catalogServiceClient;
    private final AccountServiceClient accountServiceClient;
    private final CustomerServiceClient customerServiceClient;
    private final OrderBusinessRules orderBusinessRules;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        GetBasketResponse getBasketResponse = orderBusinessRules.checkProductAvailabilityForBasket(createOrderRequest.getBasketId());

        List<OrderItem> orderItems = getBasketResponse.getBasketItems().stream()
                .map(basketItem -> {
                    OrderItem orderItem = modelMapperService.forRequest().map(basketItem, OrderItem.class);
                    return orderItem;
                })
                .collect(Collectors.toList());

        Order order = new Order();
        order.setAccountId(Integer.parseInt(getBasketResponse.getAccountId()));
        order.setOrderItems(orderItems);
        order.setBasketId(createOrderRequest.getBasketId());
        order.setTotalPrice(getBasketResponse.getTotalPrice());
        order.setId(0);

        GetAccountResponse getAccountResponse = accountServiceClient.accountGetById(Integer.parseInt(getBasketResponse.getAccountId()));

        List<GetProductResponse> getProductResponses = orderItems.stream()
                .map(product -> catalogServiceClient.productGetById(product.getProductId()))
                .collect(Collectors.toList());

        List<GetAddressResponse> getAddressResponses = getAccountResponse.getAddressId().stream()
                .map(addressId -> customerServiceClient.addressGetById(addressId))
                .collect(Collectors.toList());


        Order savedOrder = orderRepository.save(order);

        basketServiceClient.basketDeleteById(createOrderRequest.getBasketId());

        CreateOrderResponse createOrderResponse = modelMapperService.forResponse().map(order, CreateOrderResponse.class);
        createOrderResponse.setGetProductResponse(getProductResponses);
        createOrderResponse.setGetAccountResponse(getAccountResponse);
        createOrderResponse.setGetAddressResponse(getAddressResponses);
        createOrderResponse.setOrderNumber(savedOrder.getOrderNumber());

        return createOrderResponse;
    }

    @Override
    public GetOrderResponse getOrderById(int orderId) {
        orderBusinessRules.checkExistOrderByOrderId(orderId);

        // Sipariş bilgisini al
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        // Account bilgilerini al
        GetAccountResponse getAccountResponse = accountServiceClient.accountGetById(order.getAccountId());

        // Product ve Address listeleri oluştur
        List<GetProductResponse> getProductResponses = order.getOrderItems().stream()
                .map(item -> catalogServiceClient.productGetById(item.getProductId()))
                .collect(Collectors.toList());

        List<GetAddressResponse> getAddressResponses = getAccountResponse.getAddressId().stream()
                .map(addressId -> customerServiceClient.addressGetById(addressId))
                .collect(Collectors.toList());

        // GetOrderResponse oluştur ve döndür
        GetOrderResponse getOrderResponse = modelMapperService.forResponse().map(order, GetOrderResponse.class);
        getOrderResponse.setGetAccountResponse(getAccountResponse);
        getOrderResponse.setGetProductResponse(getProductResponses);
        getOrderResponse.setGetAddressResponse(getAddressResponses);
        getOrderResponse.setOrderNumber(order.getOrderNumber());

        return getOrderResponse;
    }

    @Override
    public List<GetOrderResponse> getAllOrders() {
        // Tüm sipariş bilgilerini al
        List<Order> orders = orderRepository.findAll();

        // Listeyi dönüşüm yaparak oluştur
        List<GetOrderResponse> getOrderResponses = orders.stream()
                .map(order -> {

                    // Account bilgilerini al
                    GetAccountResponse getAccountResponse = accountServiceClient.accountGetById(order.getAccountId());

                    // Product ve Address listeleri oluştur
                    List<GetProductResponse> getProductResponses = order.getOrderItems().stream()
                            .map(item -> catalogServiceClient.productGetById(item.getProductId()))
                            .collect(Collectors.toList());

                    List<GetAddressResponse> getAddressResponses = getAccountResponse.getAddressId().stream()
                            .map(addressId -> customerServiceClient.addressGetById(addressId))
                            .collect(Collectors.toList());

                    // GetOrderResponse oluştur
                    GetOrderResponse getOrderResponse = modelMapperService.forResponse().map(order, GetOrderResponse.class);
                    getOrderResponse.setGetAccountResponse(getAccountResponse);
                    getOrderResponse.setGetProductResponse(getProductResponses);
                    getOrderResponse.setGetAddressResponse(getAddressResponses);
                    getOrderResponse.setOrderNumber(order.getOrderNumber());

                    return getOrderResponse;
                })
                .collect(Collectors.toList());

        // GetAllOrdersResponse oluştur ve döndür
        return getOrderResponses;
    }

    @Override
    public void deleteOrder(int orderId) {

        orderBusinessRules.checkExistOrderByOrderId(orderId);

        orderRepository.deleteById(orderId);
    }
}
