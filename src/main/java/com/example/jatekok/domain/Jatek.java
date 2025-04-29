package com.example.jatekok.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@Entity
public class Jatek {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nev;
    private String mufaj;

    @Column(name = "kiadasi_ev")
    private LocalDate kiadasiEv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fejleszto_id", nullable = false)
    private Fejleszto fejleszto;


}
