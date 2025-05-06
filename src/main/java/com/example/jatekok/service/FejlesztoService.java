package com.example.jatekok.service;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.repository.FejlesztoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FejlesztoService {

    @Autowired
    private FejlesztoRepository fejlesztoRepository;

    public List<Fejleszto> getAllFejleszto() {
        return fejlesztoRepository.findAll();
    }

    public Fejleszto save(Fejleszto fejleszto) {
        return fejlesztoRepository.save(fejleszto);
    }

    public Fejleszto edit(Fejleszto fejleszto) {
        return fejlesztoRepository.save(fejleszto);
    }

    public Fejleszto findById(UUID id) {
        Optional<Fejleszto> optionalFejleszto =
                fejlesztoRepository.findById(id);
        if (optionalFejleszto.isPresent()) {
            return optionalFejleszto.get();
        } else {
            throw new EntityNotFoundException("Nem létezik a keresett elem!");
        }
    }

    public void deleteById(UUID id) {
        fejlesztoRepository.deleteById(id);
    }
}
