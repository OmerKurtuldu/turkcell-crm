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

        // Basket bilgilerini al
        GetBasketResponse getBasketResponse = basketServiceClient.basketGetById(createOrderRequest.getBasketId());
        Order order = new Order();
        order.setOrderItems(new ArrayList<>(getBasketResponse.getBasketItems()));  // Derin kopyalama
        order.setBasketId(getBasketResponse.getId());
        order.setTotalPrice(getBasketResponse.getTotalPrice());
        order.setId(0);  // Yeni bir sipariş oluşturduğunuzdan, ID'yi 0 olarak ayarlayın

        // Account bilgilerini al
        GetAccountResponse getAccountResponse = accountServiceClient.accountGetById(Integer.parseInt(getBasketResponse.getAccountId()));

        // Product ve Address listeleri oluştur
        List<GetProductResponse> getProductResponses = new ArrayList<>();
        List<GetAddressResponse> getAddressResponses = new ArrayList<>();

        // OrderItem listesi oluştur ve Order nesnesine ekle
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItem basketItem : getBasketResponse.getBasketItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(basketItem.getProductName());
            orderItem.setProductId(basketItem.getProductId());
            orderItem.setPrice(basketItem.getPrice());
            orderItems.add(orderItem);

            // Ürün bilgilerini al ve listeye ekle
            GetProductResponse getProductResponse = catalogServiceClient.productGetById(orderItem.getProductId());
            getProductResponses.add(getProductResponse);
        }
        order.setOrderItems(orderItems);  // Yeni orderItems listesini ayarla

        // Adres bilgilerini al ve listeye ekle
        for (int addressId : getAccountResponse.getAddressId()) {
            GetAddressResponse getAddressResponse = customerServiceClient.addressGetById(addressId);
            getAddressResponses.add(getAddressResponse);
        }

        // Order nesnesini veritabanına kaydet
        orderRepository.save(order);

        // CreateOrderResponse oluştur ve döndür
        CreateOrderResponse createOrderResponse = new CreateOrderResponse();
        createOrderResponse.setTotalPrice(order.getTotalPrice());
        createOrderResponse.setGetProductResponse(getProductResponses);
        createOrderResponse.setGetAccountResponse(getAccountResponse);
        createOrderResponse.setGetAddressResponse(getAddressResponses);
        return createOrderResponse;
    }
}


