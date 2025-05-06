package com.example.jatekok.service;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.repository.JatekRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JatekService {

    @Autowired
    private JatekRepository jatekRepository;

    public List<Jatek> getAllJatek() {
        return jatekRepository.findAll();
    }

    public Jatek save(Jatek jatek) {
        return jatekRepository.save(jatek);
    }

    public Jatek edit(Jatek jatek) {
        return jatekRepository.save(jatek);
    }

    public Jatek findById(UUID id) {
        Optional<Jatek> optionalJatek = jatekRepository.findById(id);
        if (optionalJatek.isPresent()) {
            return optionalJatek.get();
        } else {
            throw new EntityNotFoundException("Nem létezik a keresett elem!");
        }
    }

    public void deleteById(UUID id) {
        jatekRepository.deleteById(id);
    }

}
