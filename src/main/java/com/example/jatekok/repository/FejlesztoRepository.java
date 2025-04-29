package com.example.jatekok.repository;

import com.example.jatekok.domain.Fejleszto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FejlesztoRepository extends JpaRepository<Fejleszto, UUID> {
}
