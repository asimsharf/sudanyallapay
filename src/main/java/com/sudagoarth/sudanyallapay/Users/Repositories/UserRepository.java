package com.sudagoarth.sudanyallapay.Users.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

}
