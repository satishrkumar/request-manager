package net.pay.you.back.request.manager.facade.impl;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.pay.you.back.request.manager.dao.UserDAO;
import net.pay.you.back.request.manager.domain.user.User;
import net.pay.you.back.request.manager.exception.UserNotFoundException;
import net.pay.you.back.request.manager.facade.UserService;
import net.pay.you.back.request.manager.service.SequenceGeneratorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UserDAO userDAO;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;

    UUID uuid = UUID.randomUUID();

    @Override
    public User createUser(final User userModel) {
        userModel.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        return userDAO.save(userModel);
    }

    @Override
    public List<User> readAllUser() {
        return userDAO.findAll();
    }

    @Override
    public User findUserByEmailId(final String emailId) {
        Optional<User> userModelOptional = userDAO.findUserByEmailId(emailId);
        if (userModelOptional.isPresent()) {
            return userModelOptional.get();
        } else {
            logger.error("User with email id {} not found!", emailId);
//            throw new UserNotFoundException("User not found with emailId " + emailId);
        }
        return null;
    }

    @Override
    public User findUserById(final Long id) {
        Optional<User> userModelOptional = userDAO.findById(id);
        if (userModelOptional.isPresent()) {
            return userModelOptional.get();
        } else {
            logger.error("User with id {} not found!", id);
//            throw new UserNotFoundException("User not found with emailId " + id);
        }
        return null;
    }

    @Override
    public User updateExistingUser(final User userModel) {
        return userDAO.save(userModel);
    }

    @Override
    public User deleteUser(final Long id) {
        Optional<User> userModelOptional = userDAO.findById(id);
        if (userModelOptional.isPresent()) {
            userDAO.delete(userModelOptional.get());
            return userModelOptional.get();
        } else {
            logger.error("User with id {} not found!", id);
//            throw new UserNotFoundException("User not found for id " + id);
        }
        return null;
    }


}
