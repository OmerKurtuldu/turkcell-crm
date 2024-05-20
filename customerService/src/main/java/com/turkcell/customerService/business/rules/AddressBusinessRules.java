package com.turkcell.customerService.business.rules;

import com.turkcell.customerService.business.messages.Messages;
import com.turkcell.customerService.dataAccess.abstracts.AddressRepository;
import com.turkcell.customerService.entities.concretes.Address;
import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
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
