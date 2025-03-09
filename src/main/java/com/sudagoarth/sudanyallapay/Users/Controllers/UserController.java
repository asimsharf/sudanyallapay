package com.sudagoarth.sudanyallapay.Users.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudagoarth.sudanyallapay.Users.Interfaces.UserInterface;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    
    @Autowired
    private UserInterface userInterface;
    
}
