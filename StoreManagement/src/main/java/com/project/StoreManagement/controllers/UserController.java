package com.project.StoreManagement.controllers;

import com.project.StoreManagement.models.User;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.models.UserLogin;
import com.project.StoreManagement.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }


    @PostMapping
    public ResponseMessage loginUser(@RequestBody RequestMessage<UserLogin> userLogin) {
        return userServices.loginUser(userLogin);
    }

    @PostMapping("/create")
    public ResponseMessage createUser(@RequestBody @Valid RequestMessage<User> user) {
        return userServices.createUser(user);
    }
}
