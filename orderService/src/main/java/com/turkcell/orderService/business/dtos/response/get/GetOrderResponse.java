package com.turkcell.orderService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetOrderResponse {
    private int orderId;
    private String orderNumber;
    private GetAccountResponse getAccountResponse;
    private List<GetProductResponse> getProductResponse;
    private List<GetAddressResponse> getAddressResponse;
    private Double totalPrice;
}
