package com.example.jatekok.controller;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.FejlesztoService;
import com.example.jatekok.service.JatekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("fejlesztok")

public class FejlesztoController {

    @Autowired
    private FejlesztoService fejlesztoService;

    @GetMapping()
    public List<Fejleszto> getAllFejleszto() {
        return fejlesztoService.getAllFejleszto();
    }
}
