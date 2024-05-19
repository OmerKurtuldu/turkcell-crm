package com.turkcell.identityService.business.concretes;

import com.turkcell.corepackage.business.abstracts.MessageService;
import com.turkcell.identityService.business.abstracts.UserService;
import com.turkcell.identityService.business.dtos.requests.RegisterRequest;
import com.turkcell.identityService.business.messages.Messages;
import com.turkcell.identityService.dataAccess.abstracts.RoleRepository;
import com.turkcell.identityService.dataAccess.abstracts.UserRepository;
import com.turkcell.identityService.entities.concretes.Role;
import com.turkcell.identityService.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final MessageService messageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new AccessDeniedException("Giriş başarısız."));
    }

    @Override
    public void add(RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void giveRole(Integer id, Integer roleId) {
        User user = userRepository.findById(id).orElseThrow(() -> new AccessDeniedException(messageService.getMessage(Messages.BusinessErrors.NO_USER_FOUND)));
        //find role with roleId
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new AccessDeniedException(messageService.getMessage(Messages.BusinessErrors.NO_ROLE_FOUND)));
        user.getAuthorities().add(role);
        userRepository.save(user);
    }

    @Override
    public void updateEmail(Integer id, String email) {
        User user = userRepository.findById(id).orElseThrow(() -> new AccessDeniedException(messageService.getMessage(Messages.BusinessErrors.NO_USER_FOUND)));
        user.setEmail(email);
        userRepository.save(user);
    }
}
