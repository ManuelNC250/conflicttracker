package com.example.conflicttracker.controller;

import com.example.conflicttracker.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/conflicts")
public class ConflictWebController {

    @Autowired
    private ConflictService conflictService;

    @GetMapping
    public String listConflicts(Model model) {
        model.addAttribute("conflicts", conflictService.findAll());
        return "conflict-list"; // nombre de la plantilla
    }
}