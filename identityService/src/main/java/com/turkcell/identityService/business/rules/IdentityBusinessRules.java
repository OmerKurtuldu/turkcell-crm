package com.turkcell.identityService.business.rules;

import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.corepackage.utils.exceptions.types.BusinessException;
import com.turkcell.identityService.business.dtos.requests.LoginRequest;
import com.turkcell.identityService.business.messages.Messages;
import com.turkcell.identityService.dataAccess.abstracts.UserRepository;
import com.turkcell.identityService.entities.concretes.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IdentityBusinessRules {
    private final AuthenticationManager authenticationManager;
    private final MessageService messageService;


    public void authenticationControl(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BusinessException(messageService.getMessage(Messages.IdentityErrors.AuthenticationFailed));
        }
    }

//    public void userShouldBeExist(String userName){
//        Optional<User> user = userRepository.findByEmail(userName);
//        if(user.isEmpty()){
//            throw new BusinessException((messageService.getMessage(Messages.IdentityErrors.UserShouldBeExists)));
//        }
//
//    }
}