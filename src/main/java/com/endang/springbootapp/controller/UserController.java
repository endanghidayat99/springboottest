package com.endang.springbootapp.controller;

import com.endang.springbootapp.entities.User;
import com.endang.springbootapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    public String showUser(Model model){
        List<User> listUser = (List<User>) repository.findAll();
        model.addAttribute("users",listUser);
        model.addAttribute("user",new User());
        return "showUser";
    }

    @PostMapping("/addUser")
    public String addUser(@Valid @ModelAttribute User user, Model model){
        repository.save(user);

        List<User> listUser = (List<User>) repository.findAll();
        model.addAttribute("users",listUser);
        model.addAttribute("user",new User());
        return "showUser";
    }

    @RequestMapping(value="/action",params="delete",method= RequestMethod.POST)
    public String deleteUserbyId(Model model,final HttpServletRequest req){
        final Integer rowId = Integer.valueOf(req.getParameter("delete"));
        repository.deleteById(rowId);
        model.addAttribute("user",new User());
        List<User> listUser = (List<User>) repository.findAll();
        model.addAttribute("users",listUser);
        return "showUser";
    }

    @RequestMapping(value="/action",params="edit",method= RequestMethod.POST)
    public String editUserById(HttpServletRequest req, Model model) {
        final Integer id = Integer.valueOf(req.getParameter("edit"));
        Optional<User> obj = repository.findById(id);
        if (obj.isPresent()){
            model.addAttribute("user",obj);
        }else
            model.addAttribute("user",new User());
        List<User> listUser = (List<User>) repository.findAll();
        model.addAttribute("users",listUser);
        return "showUser";
    }

}
