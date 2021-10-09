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
    public User create(@RequestBody User user) {
        user.setEmailId(user.getUsername());
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public ResponseEntity authenticate(@RequestBody User user) {
        try {
            final User savedUser = userService.findUserByEmailId(user.getUsername());
            if (savedUser.getPassword().equals(user.getPassword())) {
                savedUser.setPassword(null);
                return ResponseEntity.ok(savedUser);
            } else {
                return ResponseEntity.badRequest().build();
            }

        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/read")
    public ResponseEntity<List<User>> read() {
        return ResponseEntity.ok().body(userService.readAllUser());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        final User user = userService.findUserById(id);
        if (null == user)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/readByEmailid/{emailId}")
    public ResponseEntity<User> findUserByEmailId(@PathVariable String emailId) {
        final User user = userService.findUserByEmailId(emailId);
        if (null == user)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        user.setPassword(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User userModel) {
        User user = userService.updateExistingUser(userModel);
        return new ResponseEntity<>(user,
                (null == user) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        User user = userService.deleteUser(id);
        if (null == user)
            return new ResponseEntity<>("Not Found!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Deleted Successfully!", HttpStatus.OK);
    }

}
