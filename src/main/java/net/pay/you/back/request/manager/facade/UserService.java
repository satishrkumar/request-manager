package net.pay.you.back.request.manager.facade;


import java.util.List;

import net.pay.you.back.request.manager.domain.user.User;

public interface UserService {

    User createUser(User userModel);

    List<User> readAllUser();

    User findUserByEmailId(String emailId);

    User findUserById(Long id);

    User updateExistingUser(User userModel);

    User deleteUser(Long id);
}
