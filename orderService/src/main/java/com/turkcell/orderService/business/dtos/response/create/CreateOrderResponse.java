package com.turkcell.orderService.business.dtos.response.create;

import com.turkcell.orderService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.orderService.business.dtos.response.get.GetAddressResponse;
import com.turkcell.orderService.business.dtos.response.get.GetProductResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderResponse {
    private String orderNumber;
    private GetAccountResponse getAccountResponse;
    private List<GetProductResponse> getProductResponse;
    private List<GetAddressResponse> getAddressResponse;
    private Double totalPrice;

}
