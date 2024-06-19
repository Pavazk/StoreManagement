package com.project.StoreManagement.services;

import com.project.StoreManagement.models.User;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.models.UserLogin;
import org.springframework.stereotype.Service;

@Service
public interface UserServices {

    ResponseMessage loginUser(RequestMessage<UserLogin> userLogin);
    ResponseMessage createUser(RequestMessage<User> user);
}
