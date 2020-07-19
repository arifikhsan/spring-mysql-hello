package com.example.springmysqlhello.controller;

import com.example.springmysqlhello.core.exception.UserNotFoundException;
import com.example.springmysqlhello.model.UserModel;
import com.example.springmysqlhello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    @ResponseBody
    public UserModel addNewUser(@RequestBody UserModel userModel) {
        UserModel user = new UserModel();
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        return userRepository.save(user);
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public Iterable<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/one/{id}")
    @ResponseBody
    public UserModel getUser(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping(path = "/edit/{id}")
    @ResponseBody
    public void edit(@PathVariable Integer id, @RequestBody UserModel userModel) {
        userRepository.findById(id).map(u -> {
            u.setName(userModel.getName());
            u.setEmail(userModel.getEmail());
            return userRepository.save(u);
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
