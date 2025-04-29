package com.example.jatekok.controller;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.JatekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("jatekok")
public class JatekController {

    @Autowired
    private JatekService jatekService;

    @GetMapping()
    public List<Jatek> getAllJatek() {
        return jatekService.getAllJatek();
    }
}
