package com.example.jatekok.controller.api;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.service.FejlesztoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FejlesztoRESTControllerTest {

    @Mock
    private FejlesztoService serviceMock;

    @InjectMocks
    private FejlesztoRESTController fejlesztoTest;

    @Test
    void getAllFejlesztoHappyPath() {
        List<Fejleszto> expectedFejlesztok = List.of(
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build(),
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Teszt Jatek 2. Electric Boogalo")
                        .alapitasiEv(LocalDate.now())
                        .build()
        );
        when(serviceMock.getAllFejleszto()).thenReturn(expectedFejlesztok);

        List<Fejleszto> results = fejlesztoTest.getAllFejleszto();

        Assertions.assertIterableEquals(expectedFejlesztok, results);
    }

    @Test
    void getFejlesztoWhenExistsTest() {
        Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
        UUID id = UUID.randomUUID();
        when(serviceMock.findById(id)).thenReturn(expectedFejleszto);

        Fejleszto result = fejlesztoTest.getFejleszto(id);

        Assertions.assertEquals(expectedFejleszto, result);
    }

    @Test
    void createFejlesztoTest() {
        Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
        when(serviceMock.save(expectedFejleszto)).thenReturn(expectedFejleszto);

        Fejleszto result = fejlesztoTest.createFejleszto(expectedFejleszto);

        Assertions.assertEquals(expectedFejleszto, result);
    }

    @Test
    void updateFejlesztoTest() {
        Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
        when(serviceMock.edit(expectedFejleszto)).thenReturn(expectedFejleszto);

        Fejleszto result = fejlesztoTest.updateFejleszto(expectedFejleszto);

        Assertions.assertEquals(expectedFejleszto, result);
    }

    @Test
    void deleteByIdHappyPath() {

        UUID id = UUID.randomUUID();

        fejlesztoTest.deleteFejleszto(id);

        verify(serviceMock).deleteById(id);
    }
}
