package com.turkcell.identityService.dataAccess.abstracts;

import com.turkcell.identityService.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
