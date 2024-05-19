package com.turkcell.CustomerService.business.rules;

import com.turkcell.CustomerService.business.messages.Messages;
import com.turkcell.CustomerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.CustomerService.entities.concretes.Address;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressBusinessRules {
    private final AddressRepository addressRepository;
    private final MessageService messageService;
    public void addressShouldBeExist(int id){
        Optional<Address> address = addressRepository.findById(id);
        if (address.isEmpty()){
            throw new BusinessException(messageService.getMessage(Messages.AddressErrors.AddressShouldBeExists));
        }
    }
}
