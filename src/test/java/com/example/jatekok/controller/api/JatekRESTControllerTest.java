package com.example.jatekok.controller.api;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.JatekService;
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
public class JatekRESTControllerTest {

    @Mock
    private JatekService serviceMock;

    @InjectMocks
    private JatekRESTController jatekTest;

    @Test
    void getAllJatekTest() {
        List<Jatek> expectedJatekok = List.of(
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt jatek")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("RPG")
                        .build(),
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt jatek 2")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("FPS")
                        .build(),
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt jatek 3")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("sandbox")
                        .build()

        );
        when(serviceMock.getAllJatek()).thenReturn(expectedJatekok);

        List<Jatek> results = jatekTest.getAllJatek();

        Assertions.assertIterableEquals(expectedJatekok, results);
    }

    @Test
    void getJatekWhenExistsTest() {
        Jatek expectedJatek =
                Jatek.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt jatek")
                        .kiadasiEv(LocalDate.now())
                        .mufaj("RPG")
                        .build();
        Optional<Jatek> optionalJatek = Optional.of(expectedJatek);
        UUID id = UUID.randomUUID();
        when(serviceMock.findById(id)).thenReturn(expectedJatek);

        Jatek result = jatekTest.getJatek(id);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void createJatekTest() {
        Jatek expectedJatek = Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt jatek")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();
        when(serviceMock.save(expectedJatek)).thenReturn(expectedJatek);

        Jatek result = jatekTest.createJatek(expectedJatek);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void updateJatekTest() {
        Jatek expectedJatek = Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt jatek")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();
        when(serviceMock.edit(expectedJatek)).thenReturn(expectedJatek);

        Jatek result = jatekTest.updateJatek(expectedJatek);

        Assertions.assertEquals(expectedJatek, result);
    }

    @Test
    void deleteByIdHappyPath() {

        UUID id = UUID.randomUUID();

        jatekTest.deleteJatek(id);

        verify(serviceMock).deleteById(id);
    }

}
