package com.turkcell.customerService.adapters;

import com.turkcell.customerService.business.abstracts.ICustomerCheckService;
import com.turkcell.customerService.entities.concretes.IndividualCustomer;
import com.turkcell.customerService.mernis.VFNKPSPublicSoap;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class MernisServiceAdaptor implements ICustomerCheckService {

    @SneakyThrows
    @Override
    public boolean checkIfRealPerson(IndividualCustomer individualCustomer){
        VFNKPSPublicSoap client = new VFNKPSPublicSoap();
        if (client.TCKimlikNoDogrula( Long.parseLong(individualCustomer.getNationalityNo())  ,individualCustomer.getFirstName().toUpperCase(),
                individualCustomer.getLastName().toUpperCase(),individualCustomer.getBirthDate().getYear())){
            return true;
        }
        return false;
    }
}






