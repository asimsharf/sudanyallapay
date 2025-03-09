package com.sudagoarth.sudanyallapay.Users.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sudagoarth.sudanyallapay.Users.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
