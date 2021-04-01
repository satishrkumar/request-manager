package net.pay.you.back.request.manager.facade;


import net.pay.you.back.request.manager.domain.User;

import java.util.List;

public interface UserService {

    User createUser(User userModel);

    List<User> readAllUser();

    User findUserByEmailId(String emailId);

    User updateExistingUser(User userModel);

    String deleteUser(Long id);
}
