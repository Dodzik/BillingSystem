package com.example.webapp.controllers;

import com.example.webapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "users";
    }

    @RequestMapping("/users/SortedByFirstNameAsc")
    public String getUsersAsc(Model model) {
        model.addAttribute("users", userRepository.findByOrderByFirstNameAsc());

        return "users";
    }

    @RequestMapping("/users/SortedByFirstNameDesc")
    public String getUsersDesc(Model model) {
        model.addAttribute("users", userRepository.findByOrderByFirstNameDesc());

        return "users";
    }

    @RequestMapping("/users/SortedByLastNameAsc")
    public String getUsersLastAsc(Model model) {
        model.addAttribute("users", userRepository.findByOrderByLastNameAsc());

        return "users";
    }

    @RequestMapping("/users/SortedByLastNameDesc")
    public String getUsersLastDesc(Model model) {
        model.addAttribute("users", userRepository.findByOrderByLastNameDesc());

        return "users";

    }
}
