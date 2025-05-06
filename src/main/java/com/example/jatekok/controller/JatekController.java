package com.example.jatekok.controller;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.FejlesztoService;
import com.example.jatekok.service.JatekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("jatekok")
public class JatekController {

    @Autowired
    private JatekService jatekService;

    @Autowired
    private FejlesztoService fejlesztoService;

    @GetMapping("/list")
    public String getAllJatek(Model model) {
        List<Jatek> jatekok = jatekService.getAllJatek();
        model.addAttribute("jatekok", jatekok);
        return "jatekok/jatekok";
    }

    @GetMapping("/new")
    public String createNewJatek(Model model) {
        model.addAttribute("jatek", new Jatek());
        model.addAttribute("fejleszto", fejlesztoService.getAllFejleszto());
        return "jatekok/jatekok-create";
    }

    @PostMapping()
    public String saveJatek(@ModelAttribute Jatek jatek) {
        jatekService.save(jatek);
        return "redirect:/jatekok/list";
    }

    @GetMapping("/edit/{id}")
    public String editJatek(@PathVariable UUID id, Model model) {
        Jatek jatek = jatekService.findById(id);
        model.addAttribute("jatek", jatek);
        model.addAttribute("fejleszto", fejlesztoService.getAllFejleszto());
        return "jatekok/jatek-edit";
    }

    @PostMapping("/edit")
    public String updateJatek(@ModelAttribute Jatek jatek) {
        jatekService.edit(jatek);
        return "redirect:/jatekok/list";
    }

}
