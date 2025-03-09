package com.sudagoarth.sudanyallapay.Users.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sudagoarth.sudanyallapay.Users.Interfaces.UserInterface;
import com.sudagoarth.sudanyallapay.Users.Repositories.UserRepository;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;
    
}
