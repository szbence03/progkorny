package com.example.jatekok.controller;

import com.example.jatekok.domain.Fejleszto;
import com.example.jatekok.service.FejlesztoService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FejlesztoControllerTest {

    @Mock
    private FejlesztoService serviceMock;

    @Mock
    private Model model;

    @InjectMocks
    private FejlesztoController controllerTest;


    @Test
    void getAllFejlesztoTest() {
        List<Fejleszto> fejlesztok = new ArrayList<>();
        fejlesztok.add(Fejleszto.builder().build());
        fejlesztok.add(Fejleszto.builder().build());

        when(serviceMock.getAllFejleszto()).thenReturn(fejlesztok);

        String result = controllerTest.getAllFejleszto(model);

        verify(serviceMock).getAllFejleszto();
        verify(model).addAttribute("fejlesztok", fejlesztok);
        assertEquals("fejlesztok/fejlesztok", result);
    }

    @Test
    void createNewFejlesztoTest() {
        String result = controllerTest.createNewFejleszto(model);

        verify(model).addAttribute(eq("fejleszto"), any(Fejleszto.class));
        assertEquals("fejlesztok/fejlesztok-create", result);
    }

    @Test
    void saveFejlesztoTest() {
        Fejleszto fejleszto =  Fejleszto.builder()
                .id(UUID.randomUUID())
                .nev("Fejleszto neve")
                .alapitasiEv(LocalDate.now())
                .build();

        String result = controllerTest.saveFejleszto(fejleszto);

        verify(serviceMock).save(fejleszto);
        assertEquals("redirect:/fejlesztok/list", result);
    }

    @Test
    void editFejlesztoTest() {
        UUID id = UUID.randomUUID();
        Fejleszto fejleszto =  Fejleszto.builder()
                .id(UUID.randomUUID())
                .nev("Fejleszto neve")
                .alapitasiEv(LocalDate.now())
                .build();
        when(serviceMock.findById(id)).thenReturn(fejleszto);

        String result = controllerTest.editFejleszto(id, model);

        verify(serviceMock).findById(id);
        verify(model).addAttribute("fejleszto", fejleszto);
        assertEquals("fejlesztok/fejleszto-edit", result);
    }

    @Test
    void updateFejlesztoTest() {
        Fejleszto fejleszto =  Fejleszto.builder()
                .id(UUID.randomUUID())
                .nev("Fejleszto neve")
                .alapitasiEv(LocalDate.now())
                .build();

        String result = controllerTest.updateFejleszto(fejleszto);

        verify(serviceMock).edit(fejleszto);
        assertEquals("redirect:/fejlesztok/list", result);
    }
}