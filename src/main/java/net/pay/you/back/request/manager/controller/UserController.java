package net.pay.you.back.request.manager.controller;

import net.pay.you.back.request.manager.domain.User;
import net.pay.you.back.request.manager.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registerUser")
    public User create(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @GetMapping("/read")
    public List<User> read() {
        return userService.readAllUser();
    }

    @GetMapping("/read/{emailId}")
    public User findUserByEmailId(@PathVariable String emailId) {
        return userService.findUserByEmailId(emailId);
    }

    @PutMapping("/update")
    public User update(@RequestBody User userModel) {
        return userService.updateExistingUser(userModel);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
