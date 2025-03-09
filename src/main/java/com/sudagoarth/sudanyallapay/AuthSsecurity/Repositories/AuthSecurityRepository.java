package com.sudagoarth.sudanyallapay.AuthSsecurity.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.AuthSsecurity.Entities.AuthSecurity;
import com.sudagoarth.sudanyallapay.Users.Entities.User;

import java.util.Optional;

@Repository
public interface AuthSecurityRepository extends JpaRepository<AuthSecurity, Long> {
    Optional<AuthSecurity> findByCodeAndIsUsedFalse(String otpCode);

    Optional<AuthSecurity> findByCodeAndIsUsedFalse(User user);
}
