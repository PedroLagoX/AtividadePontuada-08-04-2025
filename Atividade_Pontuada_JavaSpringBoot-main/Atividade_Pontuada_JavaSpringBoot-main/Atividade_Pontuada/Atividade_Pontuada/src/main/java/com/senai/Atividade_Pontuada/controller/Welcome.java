package com.senai.Atividade_Pontuada.controller;

import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class Welcome {
    @GetMapping
    public String exibirMensagem() {
        return "Bem-vindo ao meu Spring Boot";
    }
}
