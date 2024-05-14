package com.turkcell.accountService.business.abstracts;

import com.turkcell.accountService.business.dtos.request.created.CreatedAccountRequest;
import com.turkcell.accountService.business.dtos.request.updated.UpdatedAccountRequest;
import com.turkcell.accountService.business.dtos.response.created.CreatedAccountResponse;
import com.turkcell.accountService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.accountService.business.dtos.response.getAll.GetAllAccountResponse;
import com.turkcell.accountService.business.dtos.response.updated.UpdatedAccountResponse;

import java.util.List;

public interface AccountService {
    CreatedAccountResponse add(CreatedAccountRequest createdAccountRequest);
    UpdatedAccountResponse update(UpdatedAccountRequest updatedAccountRequest);
    GetAccountResponse getById(int id);
    List<GetAllAccountResponse> getAll();
    void delete(int id);
}
