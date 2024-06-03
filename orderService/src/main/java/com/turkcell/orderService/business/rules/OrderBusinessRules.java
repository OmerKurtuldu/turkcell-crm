package com.turkcell.orderService.business.rules;

import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.orderService.api.clients.BasketServiceClient;
import com.turkcell.orderService.business.dtos.response.get.GetBasketResponse;
import com.turkcell.orderService.business.messages.Messages;
import com.turkcell.orderService.dataAccess.OrderRepository;
import com.turkcell.orderService.entities.Order;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderBusinessRules {
    private final OrderRepository orderRepository;
    private final MessageService messageService;
    private final BasketServiceClient basketServiceClient;

    public GetBasketResponse checkProductAvailabilityForBasket(String basketId) {
        try {
            GetBasketResponse getBasketResponse = basketServiceClient.basketGetById(basketId);
            return getBasketResponse;
        } catch (FeignException e) {
            throw new BusinessException(messageService.getMessage(Messages.OrderErrors.BasketRegistrationShouldBeExist));
        }
    }

    public void checkExistOrderByOrderId(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.OrderErrors.OrderShouldBeExist));
        }
    }

    public Order checkExistOrderId(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new BusinessException(messageService.getMessage(Messages.OrderErrors.OrderShouldBeExist));
        }
        return order.get();
    }
}
