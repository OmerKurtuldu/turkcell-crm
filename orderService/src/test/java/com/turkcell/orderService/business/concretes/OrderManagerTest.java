package com.turkcell.orderService.business.concretes;

import com.turkcell.corepackage.utils.mappers.ModelMapperService;
import com.turkcell.orderService.api.clients.AccountServiceClient;
import com.turkcell.orderService.api.clients.BasketServiceClient;
import com.turkcell.orderService.api.clients.CatalogServiceClient;
import com.turkcell.orderService.api.clients.CustomerServiceClient;
import com.turkcell.orderService.business.dtos.request.create.CreateOrderRequest;
import com.turkcell.orderService.business.dtos.response.create.CreateOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.orderService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.orderService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.orderService.business.dtos.response.get.GetOrderItemResponse;
import com.turkcell.orderService.business.dtos.response.get.GetOrderResponse;
import com.turkcell.orderService.business.dtos.response.get.GetProductResponse;
import com.turkcell.orderService.business.dtos.response.getAll.GetAllOrderResponse;
import com.turkcell.orderService.business.rules.OrderBusinessRules;
import com.turkcell.orderService.dataAccess.OrderRepository;
import com.turkcell.orderService.entities.Order;
import com.turkcell.orderService.entities.OrderItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderManagerTest {

    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private BasketServiceClient basketServiceClient;
    @Mock
    private CatalogServiceClient catalogServiceClient;
    @Mock
    private AccountServiceClient accountServiceClient;
    @Mock
    private CustomerServiceClient customerServiceClient;
    @Mock
    private OrderBusinessRules orderBusinessRules;
    @InjectMocks
    private OrderManager orderManager;

    private void modelMapperForRequest() {
        ModelMapper modelMapper = mock(ModelMapper.class);
        when(modelMapperService.forRequest()).thenReturn(modelMapper);
    }

    @Test
    void createOrderWhenSuccess() {
        // Given
        modelMapperForRequest();

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setBasketId("basket123");

        GetBasketResponse getBasketResponse = new GetBasketResponse();
        getBasketResponse.setBasketItems(Arrays.asList(new GetOrderItemResponse(1, "Product1", 100.00)));
        getBasketResponse.setTotalPrice(100.0);
        getBasketResponse.setAccountId("123");

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1);

        GetAccountResponse getAccountResponse = new GetAccountResponse();
        getAccountResponse.setAddressId(Arrays.asList(1, 2));

        when(orderBusinessRules.checkProductAvailabilityForBasket(anyString())).thenReturn(getBasketResponse);
        when(modelMapperService.forRequest().map(any(GetOrderItemResponse.class), eq(OrderItem.class))).thenReturn(orderItem);
        when(accountServiceClient.accountGetById(anyInt())).thenReturn(getAccountResponse);
        when(catalogServiceClient.productGetById(anyInt())).thenReturn(new GetProductResponse());
        when(customerServiceClient.addressGetById(anyInt())).thenReturn(new GetAddressResponse());

        // When
        CreateOrderResponse createOrderResponse = orderManager.createOrder(createOrderRequest);

        // Then
        Assertions.assertNotNull(createOrderResponse);
        Assertions.assertEquals(100.0, createOrderResponse.getTotalPrice());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getOrderByIdWhenSuccess() {
        // Given
        int orderId = 1;

        Order order = new Order();
        order.setId(orderId);
        order.setBasketId("basket123");
        order.setOrderItems(Collections.singletonList(new OrderItem()));

        GetBasketResponse getBasketResponse = new GetBasketResponse();
        getBasketResponse.setAccountId("123");
        getBasketResponse.setBasketItems(Collections.singletonList(new GetOrderItemResponse(1, "Product1", 100.00)));

        GetAccountResponse getAccountResponse = new GetAccountResponse();
        getAccountResponse.setAddressId(Arrays.asList(1, 2));

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(basketServiceClient.basketGetById(anyString())).thenReturn(getBasketResponse);
        when(accountServiceClient.accountGetById(anyInt())).thenReturn(getAccountResponse);
        when(catalogServiceClient.productGetById(anyInt())).thenReturn(new GetProductResponse());
        when(customerServiceClient.addressGetById(anyInt())).thenReturn(new GetAddressResponse());

        // When
        GetOrderResponse getOrderResponse = orderManager.getOrderById(orderId);

        // Then
        Assertions.assertNotNull(getOrderResponse);
        Assertions.assertEquals(orderId, getOrderResponse.getOrderId());
    }

    @Test
    void getAllOrdersWhenSuccess() {
        // Given
        Order order = new Order();
        order.setId(1);
        order.setBasketId("basket123");
        order.setOrderItems(Collections.singletonList(new OrderItem()));

        List<Order> orders = Arrays.asList(order);

        GetBasketResponse getBasketResponse = new GetBasketResponse();
        getBasketResponse.setAccountId("123");
        getBasketResponse.setBasketItems(Collections.singletonList(new GetOrderItemResponse(1, "Product1", 100.00)));

        GetAccountResponse getAccountResponse = new GetAccountResponse();
        getAccountResponse.setAddressId(Arrays.asList(1, 2));

        when(orderRepository.findAll()).thenReturn(orders);
        when(basketServiceClient.basketGetById(anyString())).thenReturn(getBasketResponse);
        when(accountServiceClient.accountGetById(anyInt())).thenReturn(getAccountResponse);
        when(catalogServiceClient.productGetById(anyInt())).thenReturn(new GetProductResponse());
        when(customerServiceClient.addressGetById(anyInt())).thenReturn(new GetAddressResponse());

        // When
        GetAllOrderResponse getAllOrderResponse = orderManager.getAllOrders();

        // Then
        Assertions.assertNotNull(getAllOrderResponse);
        Assertions.assertEquals(1, getAllOrderResponse.getOrders().size());
    }

    @Test
    void deleteOrderWhenSuccess() {
        // Given
        int orderId = 1;

        doNothing().when(orderBusinessRules).checkExistOrderByOrderId(orderId);

        // When
        orderManager.deleteOrder(orderId);

        // Then
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
