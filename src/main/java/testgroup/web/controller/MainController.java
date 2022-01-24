package testgroup.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import testgroup.web.model.Role;
import testgroup.web.model.User;
import testgroup.web.repositories.RoleRepository;
import testgroup.web.repositories.UserRepository;
import testgroup.web.service.RoleService;
import testgroup.web.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleServise;

    @GetMapping("/")
    public String index (Model model) {
        return "index";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/admin")
    public String AdminPage(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute ("users", users);
        return "admin";
    }

    @GetMapping("/add")
    public String getCreateUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles",roleServise.findAll());
        return "add";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam("roles") ArrayList<Long> roles) {

        Set<Role> roleSet = new HashSet<>((Collection<? extends Role>)
                roleServise.findAllById(roles));
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("roles", roleServise.findAll());
        return "edit";
    }

    @PostMapping ("/edit")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam("roles") ArrayList<Long> roles) {

        Set<Role> roleSet = new HashSet<>((Collection<? extends Role>)
                roleServise.findAllById(roles));
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin";
    }



    @PostMapping("/{id}/remove")
    public String userDelete (@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findById(id);
        userService.delete(user);

        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String pageForUser (Model model, Principal principal) {
        model.addAttribute("user",userService.getUserByUsername(principal.getName()));
        return "user";
    }




}
