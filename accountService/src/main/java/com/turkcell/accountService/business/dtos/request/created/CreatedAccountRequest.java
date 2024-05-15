package com.turkcell.accountService.business.dtos.request.created;

import com.turkcell.accountService.entities.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatedAccountRequest {

    private String accountName;
    private String accountNumber;
    private Boolean status;
    private Action action;
    private Set<Integer> accountTypes;
    private int customerId;
    private int addressId;
}
