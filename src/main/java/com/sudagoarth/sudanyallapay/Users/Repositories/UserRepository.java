package com.sudagoarth.sudanyallapay.Users.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    User findByPhoneNumber(String phoneNumber);
@Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
User findByEmail(@Param("email") String email);


}
