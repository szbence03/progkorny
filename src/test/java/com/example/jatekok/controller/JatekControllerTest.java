package com.example.jatekok.controller;

import com.example.jatekok.domain.Jatek;
import com.example.jatekok.service.FejlesztoService;
import com.example.jatekok.service.JatekService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JatekControllerTest {

    @Mock
    private JatekService serviceMock;

    @Mock
    private FejlesztoService fejlesztoMock;

    @Mock
    private Model model;

    @InjectMocks
    private JatekController controllerTest;


    @Test
    void getAllJatekTest() {
        List<Jatek> jatekok = new ArrayList<>();
        jatekok.add(Jatek.builder().build());
        jatekok.add(Jatek.builder().build());

        when(serviceMock.getAllJatek()).thenReturn(jatekok);

        String result = controllerTest.getAllJatek(model);

        verify(serviceMock).getAllJatek();
        verify(model).addAttribute("jatekok", jatekok);
        assertEquals("jatekok/jatekok", result);
    }

    @Test
    void createNewJatekTest() {
        String result = controllerTest.createNewJatek(model);

        verify(model).addAttribute(eq("jatek"), any(Jatek.class));
        assertEquals("jatekok/jatekok-create", result);
    }

    @Test
    void saveJatekTest() {
        Jatek jatek =  Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt jatek")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();

        String result = controllerTest.saveJatek(jatek);

        verify(serviceMock).save(jatek);
        assertEquals("redirect:/jatekok/list", result);
    }

    @Test
    void editJatekTest() {
        UUID id = UUID.randomUUID();
        Jatek jatek =  Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt jatek")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();
        when(serviceMock.findById(id)).thenReturn(jatek);

        String result = controllerTest.editJatek(id, model);

        verify(serviceMock).findById(id);
        verify(model).addAttribute("jatek", jatek);
        assertEquals("jatekok/jatek-edit", result);
    }

    @Test
    void updateJatekTest() {
        Jatek jatek =  Jatek.builder()
                .id(UUID.randomUUID())
                .nev("Teszt jatek")
                .kiadasiEv(LocalDate.now())
                .mufaj("RPG")
                .build();

        String result = controllerTest.updateJatek(jatek);

        verify(serviceMock).edit(jatek);
        assertEquals("redirect:/jatekok/list", result);
    }
}
