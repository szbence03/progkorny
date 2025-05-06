package com.example.jatekok.controller.api;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.service.FejlesztoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/fejlesztok")
public class FejlesztoRESTController {

    @Autowired
    private FejlesztoService fejlesztoService;

    @GetMapping()
    public List<Fejleszto> getAllFejleszto() {
        return fejlesztoService.getAllFejleszto();
    }

    @GetMapping("/{id}")
    public Fejleszto getFejleszto(@PathVariable UUID id) {
        return fejlesztoService.findById(id);
    }

    @PostMapping("/create")
    public Fejleszto createFejleszto(@RequestBody Fejleszto fejleszto) {
        return fejlesztoService.save(fejleszto);
    }

    @PostMapping("/update")
    public Fejleszto updateFejleszto(@RequestBody Fejleszto fejleszto) {
        return fejlesztoService.edit(fejleszto);
    }

    @DeleteMapping("/{id}")
    public void deleteFejleszto(@PathVariable UUID id) {
        fejlesztoService.deleteById(id);
    }

}
