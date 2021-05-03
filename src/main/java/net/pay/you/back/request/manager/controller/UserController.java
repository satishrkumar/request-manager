package net.pay.you.back.request.manager.controller;

import java.util.List;

import net.pay.you.back.request.manager.domain.user.User;
import net.pay.you.back.request.manager.exception.UserNotFoundException;
import net.pay.you.back.request.manager.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registerUser")
    public User create(@RequestBody User user)
    {
        user.setEmailId(user.getUsername());
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody User user)
    {
     try {
         final User savedUser = userService.findUserByEmailId(user.getUsername());
         if(savedUser.getPassword().equals(user.getPassword())){
             savedUser.setPassword(null);
             return ResponseEntity.ok(savedUser);
         }else{
             return ResponseEntity.badRequest().build();
         }

     }catch (UserNotFoundException userNotFoundException){
         return ResponseEntity.notFound().build();
     }
    }

    @GetMapping("/read")
    public List<User> read() {
        return userService.readAllUser();
    }

    @GetMapping("/read/{id}")
    public User findUserByEmailId(@PathVariable Long id) {
        final User userById = userService.findUserById(id);
        userById.setPassword(null);
        return userById;
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
