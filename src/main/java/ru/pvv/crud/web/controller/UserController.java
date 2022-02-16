package ru.pvv.crud.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.pvv.crud.business.model.User;
import ru.pvv.crud.business.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping("")
    public String hello(Model model) {
        return "users/hello";
    }

    @GetMapping("/users")
    public String showAll(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/all";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {

        return "users/new";
    }

    @PostMapping("/users/new")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult /*Всегда идет после аргумента с анотацией валид. Сюда пишутся ошибки валидности*/) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error POST");
            return "users/new";
        }
        userService.saveUser(user.getName(), user.getEmail(), (byte) user.getAge());

        return "redirect:/users";
    }

    @GetMapping("/users/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute(user);
        return "users/showUser";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") long id){
        if (bindingResult.hasErrors()){
            return "users/edit";
        }
        userService.updateUser(user);
        return "redirect:/users";
    }


    @DeleteMapping(value = "/users/{id}")
    public String remove(@PathVariable("id") long id){
        userService.removeUserById(id);
        return "redirect:/users";
    }

}
