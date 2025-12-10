

package com.example.bankingapp.controller;

import com.example.bankingapp.model.Account;
import com.example.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam double balance,
                         Model model) {
        Account acc = service.createAccount(name, balance);
        model.addAttribute("msg", "Account Created! ID: " + acc.getId());
        return "index";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam Long id,
                          @RequestParam double amount,
                          Model model) {
        model.addAttribute("msg", service.deposit(id, amount));
        return "index";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Long id,
                           @RequestParam double amount,
                           Model model) {
        model.addAttribute("msg", service.withdraw(id, amount));
        return "index";
    }
}
