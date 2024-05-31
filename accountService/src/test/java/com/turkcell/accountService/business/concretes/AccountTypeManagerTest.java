package com.turkcell.accountService.business.concretes;

import com.turkcell.accountService.business.rules.AccountTypeBusinessRules;
import com.turkcell.accountService.dataAccess.abstracts.AccountTypesRepository;
import com.turkcell.accountService.entities.concretes.AccountType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountTypeManagerTest {

    @Mock
    private AccountTypesRepository accountTypesRepository;
    @Mock
    private AccountTypeBusinessRules accountTypeBusinessRules;
    @InjectMocks
    private AccountTypeManager accountTypeManager;

    @Test
    void getByIdWhenSuccess() {
        // Given
        int accountTypeId = 1;
        AccountType accountType = new AccountType();
        accountType.setId(accountTypeId);
        accountType.setAccountTypeName("Savings");

        when(accountTypesRepository.findById(accountTypeId)).thenReturn(Optional.of(accountType));

        // When
        AccountType result = accountTypeManager.getById(accountTypeId);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(accountType.getAccountTypeName(), result.getAccountTypeName());
        verify(accountTypesRepository, times(1)).findById(accountTypeId);
        verify(accountTypeBusinessRules, times(1)).accountTypeShouldBeExist(accountTypeId);
    }

    @Test
    void getByIdWhenAccountTypeDoesNotExist() {
        // Given
        int accountTypeId = 1;

        doThrow(new RuntimeException("Account type not found")).when(accountTypeBusinessRules).accountTypeShouldBeExist(accountTypeId);

        // When & Then
        Assertions.assertThrows(RuntimeException.class, () -> {
            accountTypeManager.getById(accountTypeId);
        });

        verify(accountTypesRepository, never()).findById(accountTypeId);
        verify(accountTypeBusinessRules, times(1)).accountTypeShouldBeExist(accountTypeId);
    }
}
