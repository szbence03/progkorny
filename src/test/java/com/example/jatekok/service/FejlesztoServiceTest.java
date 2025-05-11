package com.example.jatekok.service;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.repository.FejlesztoRepository;
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
public class FejlesztoServiceTest {

    @Mock
    private FejlesztoRepository fejlesztoRepositoryMock;

    @InjectMocks
    private FejlesztoService fejlesztoTest;

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
        when(fejlesztoRepositoryMock.findAll()).thenReturn(expectedFejlesztok);

       List<Fejleszto> results = fejlesztoTest.getAllFejleszto();

       Assertions.assertIterableEquals(expectedFejlesztok, results);
    }

    @Test
    void findByIdWhenExists() {
       Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
       Optional<Fejleszto> optionalFejleszto = Optional.of(expectedFejleszto);
       UUID id = UUID.randomUUID();
       when(fejlesztoRepositoryMock.findById(id)).thenReturn(optionalFejleszto);

       Fejleszto result = fejlesztoTest.findById(id);

       Assertions.assertEquals(expectedFejleszto, result);
    }

    @Test
    void findByIdWhenNotExists() {
        Optional<Fejleszto> optionalFejleszto = Optional.empty();
        UUID id = UUID.randomUUID();
        when(fejlesztoRepositoryMock.findById(id)).thenReturn(optionalFejleszto);

        Assertions.assertThrows(EntityNotFoundException.class, ()-> fejlesztoTest.findById(id));

    }

    @Test
    void deleteByIdHappyPath() {

        UUID id = UUID.randomUUID();

        fejlesztoTest.deleteById(id);

        verify(fejlesztoRepositoryMock).deleteById(id);
    }

    @Test
    void saveTest() {
        Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
        when(fejlesztoRepositoryMock.save(expectedFejleszto)).thenReturn(expectedFejleszto);

        Fejleszto result = fejlesztoTest.save(expectedFejleszto);

        Assertions.assertEquals(expectedFejleszto, result);
    }

    @Test
    void editTest() {
        Fejleszto expectedFejleszto =
                Fejleszto.builder()
                        .id(UUID.randomUUID())
                        .nev("Fejleszto neve")
                        .alapitasiEv(LocalDate.now())
                        .build();
        when(fejlesztoRepositoryMock.save(expectedFejleszto)).thenReturn(expectedFejleszto);

        Fejleszto result = fejlesztoTest.save(expectedFejleszto);

        Assertions.assertEquals(expectedFejleszto, result);
    }
}
