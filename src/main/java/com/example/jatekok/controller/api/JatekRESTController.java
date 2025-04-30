package com.example.jatekok.controller.api;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.JatekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/jatekok")
public class JatekRESTController {

    @Autowired
    private JatekService jatekService;

    @GetMapping()
    public List<Jatek> getAllJatek() {
        return jatekService.getAllJatek();
    }

    @GetMapping("/{id}")
    public Jatek getJatek(@PathVariable UUID id) {
        return jatekService.findById(id);
    }

    @PostMapping("/create")
    public Jatek createJatek(@RequestBody Jatek jatek) {
        return jatekService.save(jatek);
    }

    @PostMapping("/update")
    public Jatek updateJatek(@RequestBody Jatek jatek) {
        return jatekService.edit(jatek);
    }

    @DeleteMapping("/{id}")
    public void deleteJatek(@PathVariable UUID id) {
        jatekService.deleteById(id);
    }
}
