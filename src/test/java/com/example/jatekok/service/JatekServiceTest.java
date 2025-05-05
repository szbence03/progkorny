package com.example.jatekok.service;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.repository.JatekRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JatekServiceTest {

    @Mock
    private JatekRepository jatekRepositoryMock;

    @InjectMocks
    private JatekService jatekServiceTest;


    @Test
    void getAllJatekTest() {
        List<Jatek> expectedJatekok = List.of(
            Jatek.builder()
                    .id(UUID.randomUUID())
                    .nev("Teszt játék")
                    .kiadasiEv(LocalDate.now())
                    .mufaj("RPG")
                    .build(),
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt játék 2")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("FPS")
                        .build(),
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt játék 3")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("sandbox")
                        .build()

        );
        when(jatekRepositoryMock.findAll()).thenReturn(expectedJatekok);

        List<Jatek> results = jatekServiceTest.getAllJatek();

        Assertions.assertIterableEquals(expectedJatekok, results);
    }

    @Test
    void findByIdWhenExists() {
        Jatek expectedJatek =
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt játék")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("RPG")
                        .build();
        Optional<Jatek> optionalJatek = Optional.of(expectedJatek);
        UUID id = UUID.randomUUID();
        when(jatekRepositoryMock.findById(id)).thenReturn(optionalJatek);

        Jatek result = jatekServiceTest.findById(id);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void findByIdWhenNotExists() {
        Optional<Jatek> optionalJatek = Optional.empty();
        UUID id = UUID.randomUUID();
        when(jatekRepositoryMock.findById(id)).thenReturn(optionalJatek);

        Assertions.assertThrows(EntityNotFoundException.class, ()-> jatekServiceTest.findById(id));

    }


    @Test
    void saveTest() {
       Jatek expectedJatek = Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt játék")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();
        when(jatekRepositoryMock.save(expectedJatek)).thenReturn(expectedJatek);

        Jatek result = jatekServiceTest.save(expectedJatek);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void editTest() {
        Jatek expectedJatek = Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt játék")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();
        when(jatekRepositoryMock.save(expectedJatek)).thenReturn(expectedJatek);

        Jatek result = jatekServiceTest.save(expectedJatek);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void deleteByIdTest() {

        UUID id = UUID.randomUUID();

        jatekServiceTest.deleteById(id);

        verify(jatekRepositoryMock).deleteById(id);
    }


}
