package com.example.jatekok.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fejleszto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nev;

    @Column(name = "alapitasi_ev")
    private LocalDate alapitasiEv;

    @OneToMany(mappedBy = "fejleszto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jatek> jatekok;
}
