package com.project.StoreManagement.services;

import com.project.StoreManagement.exceptions.AlreadyInUseException;
import com.project.StoreManagement.exceptions.AuthenticationFailedException;
import com.project.StoreManagement.exceptions.NotFoundException;
import com.project.StoreManagement.models.RequestMessage;
import com.project.StoreManagement.models.ResponseMessage;
import com.project.StoreManagement.models.User;
import com.project.StoreManagement.models.UserLogin;
import com.project.StoreManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.project.StoreManagement.services.implementations.ArticleServicesImplement.createResponse;

@Service
public class UserServicesImplement implements UserServices{

    private final UserRepository userRepository;

    @Autowired
    public UserServicesImplement(UserRepository categoryRepository) {
        this.userRepository = categoryRepository;
    }

    @Override
    public ResponseMessage loginUser(RequestMessage<UserLogin> userLoginRequestMessage) {

        Optional<User> userOptional = userRepository.findByEmailUser(userLoginRequestMessage.getObject().getEmailUser());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println(user.getPasswordUser());
            if (user.getPasswordUser().equals(userLoginRequestMessage.getObject().getPasswordUser())) {
                return createResponse(JWTService.getToken(new HashMap<>(), (UserDetails) user), HttpStatus.OK);
            }
        }
        throw new AuthenticationFailedException("Credenciales incorrectas");
    }

    @Override
    public ResponseMessage createUser(RequestMessage<User> user) {
        if (userRepository.findByEmailUser(user.getObject().getEmailUser()).isPresent()) {
            throw new AlreadyInUseException("Usuario ya existe");
        }
        userRepository.save(user.getObject());
        return createResponse("Usuario creado correctamente", HttpStatus.OK);
    }
}
