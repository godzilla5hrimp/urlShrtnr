package com.godzilla5hrimp.url.shortener.project.service;

import com.godzilla5hrimp.url.shortener.project.exceptions.WrongPasswordException;
import com.godzilla5hrimp.url.shortener.project.model.UserModel;
import com.godzilla5hrimp.url.shortener.project.repository.UserModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UrlProcessingService.class);

    @Autowired
    UserModelRepository userModelRepository;

    public boolean registerUser(String userName, String password) {
        UserModel newUser = new UserModel();
        newUser.setUserName(userName);
        newUser.setPassword(Sha512DigestUtils.shaHex(password));

        userModelRepository.save(newUser);
        logger.info("User has been saved: userName{}", userName);
        return true;
    }

    public boolean loginUser(String userName, String password) {
        UserModel foundUser = userModelRepository.findByUserName(userName);
        String decPsw = Sha512DigestUtils.shaHex(password);
        if (foundUser != null
                && foundUser.getPassword().equals(decPsw)) {
            return true;
        } else {
            throw new WrongPasswordException("Wrong password for the specified user!");
        }
    }

}
