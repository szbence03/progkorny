package com.example.jatekok.controller;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.service.FejlesztoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("fejlesztok")

public class FejlesztoController {

    @Autowired
    private FejlesztoService fejlesztoService;


    @GetMapping("/list")
    public String getAllFejleszto(Model model) {
        List<Fejleszto> fejlesztok = fejlesztoService.getAllFejleszto();
        model.addAttribute("fejlesztok", fejlesztok);
        return "fejlesztok/fejlesztok";
    }

    @GetMapping("/new")
    public String createNewFejleszto(Model model) {
        model.addAttribute("fejleszto", new Fejleszto());
        return "fejlesztok/fejlesztok-create";
    }

    @PostMapping()
    public String saveFejleszto(@ModelAttribute Fejleszto fejleszto) {
        fejlesztoService.save(fejleszto);
        return "redirect:/fejlesztok/list";
    }

    @GetMapping("/edit/{id}")
    public String editFejleszto(@PathVariable UUID id, Model model) {
        Fejleszto fejleszto = fejlesztoService.findById(id);
        model.addAttribute("fejleszto", fejleszto);
        return "fejlesztok/fejleszto-edit";
    }

    @PostMapping("/edit")
    public String updateFejleszto(@ModelAttribute Fejleszto fejleszto) {
        fejlesztoService.edit(fejleszto);
        return "redirect:/fejlesztok/list";
    }
}
