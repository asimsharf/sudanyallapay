package com.sudagoarth.sudanyallapay.Users.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Users.Entities.AuthSecurity;

import java.util.Optional;

@Repository
public interface AuthSecurityRepository extends JpaRepository<AuthSecurity, Long> {
    Optional<AuthSecurity> findByCodeAndIsUsedFalse(String otpCode);

    Optional<AuthSecurity> findByUserIdAndIsUsedFalse(Long id);
}
