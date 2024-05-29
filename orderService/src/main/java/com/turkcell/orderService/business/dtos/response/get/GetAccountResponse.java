package com.turkcell.orderService.business.dtos.response.get;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAccountResponse {

    private String accountName;
    private String accountNumber;
    private Boolean status;
    private List<String> accountTypeName;
    private int customerId;
    private List<Integer> addressId;

}
