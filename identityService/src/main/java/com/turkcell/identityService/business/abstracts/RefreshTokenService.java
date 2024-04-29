package com.turkcell.identityService.business.abstracts;


import com.turkcell.identityService.entities.concretes.RefreshToken;
import com.turkcell.identityService.entities.concretes.User;

public interface RefreshTokenService {
  RefreshToken create(User user);
  RefreshToken verifyRefreshToken(String token);
}
