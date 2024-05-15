package com.turkcell.accountService.business.dtos.request.updated;

import com.turkcell.accountService.entities.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdatedAccountRequest {

    private int id;
    private String accountName;
    private String AccountNumber;
    private Boolean status;
    private Action action;
    private Set<Integer> accountTypes;
    private int customerId;

}
