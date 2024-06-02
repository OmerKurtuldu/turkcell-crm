package com.turkcell.accountService.api.controllers;

import com.turkcell.accountService.business.abstracts.AccountService;
import com.turkcell.accountService.business.dtos.request.created.CreatedAccountRequest;
import com.turkcell.accountService.business.dtos.request.updated.UpdatedAccountRequest;
import com.turkcell.accountService.business.dtos.response.created.CreatedAccountResponse;
import com.turkcell.accountService.business.dtos.response.get.GetAccountResponse;
import com.turkcell.accountService.business.dtos.response.getAll.GetAllAccountResponse;
import com.turkcell.accountService.business.dtos.response.updated.UpdatedAccountResponse;
import com.turkcell.commonpackage.utils.dto.ClientResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accountservice/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountResponse add(@Valid @RequestBody CreatedAccountRequest createdAccountRequest) {
        return accountService.add(createdAccountRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedAccountResponse update(@Valid @RequestBody UpdatedAccountRequest updatedAccountRequest) {
        return accountService.update(updatedAccountRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetAccountResponse getById(@PathVariable int id) {
        return accountService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllAccountResponse> getAll() {
        return accountService.getAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        accountService.delete(id);
    }

    @GetMapping("/accountClient/{id}")
    public ClientResponse checkIfAccountAvailable(@PathVariable int id) {
        return accountService.checkIfAccountAvailable(id);
    }

}
