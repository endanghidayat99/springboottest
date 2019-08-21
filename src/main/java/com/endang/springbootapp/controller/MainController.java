package com.endang.springbootapp.controller;

import com.endang.springbootapp.entities.Employee;
import com.endang.springbootapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("message","Hello Spring Boot");
        return "hello";
    }

    @GetMapping("/employee")
    public String inputEmployee(Model model){
        model.addAttribute("employee",new Employee());
//        List<Employee> listData = (List<Employee>) employeeRepository.findAll();
//        model.addAttribute("data",listData);
        return "create";
    }

    @PostMapping("/addemployee")
    public String createEmployee(@Valid @ModelAttribute Employee emp,Model model){
        employeeRepository.save(emp);

//        List<Employee> listData = (List<Employee>) employeeRepository.findAll();
//        model.addAttribute("data",listData);
        return "success";
    }

    @GetMapping("/employee/{id}")
    public String getEmployee(@PathVariable("id") Integer id, Model model){

        Optional<Employee> obj = employeeRepository.findById(id);
        if (obj.isPresent())
            model.addAttribute("employee",obj.get());
        else
            model.addAttribute("employee",new Employee());

        return "create";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id, Model model){
        employeeRepository.deleteById(id);
        return "success";
    }
}
