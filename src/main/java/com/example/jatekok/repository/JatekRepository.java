package com.example.jatekok.repository;

import com.example.jatekok.domain.Jatek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JatekRepository extends JpaRepository<Jatek, UUID> {

}
