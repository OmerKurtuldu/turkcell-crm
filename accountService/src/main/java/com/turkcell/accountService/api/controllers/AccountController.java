package com.turkcell.accountService.api.controllers;

import com.turkcell.accountService.business.abstracts.AccountService;
import com.turkcell.accountService.business.dtos.request.created.CreatedAccountRequest;
import com.turkcell.accountService.business.dtos.response.created.CreatedAccountResponse;
import com.turkcell.accountService.entities.concretes.Account;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/accountservice/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedAccountResponse add(@Valid @RequestBody CreatedAccountRequest createdAccountRequest){
        return accountService.add(createdAccountRequest);
    }
}
