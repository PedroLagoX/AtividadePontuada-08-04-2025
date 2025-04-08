package com.senai.Atividade_Pontuada.controller;

import com.senai.Atividade_Pontuada.model.Funcionario;
import com.senai.Atividade_Pontuada.repository.FuncionarioRepository;
import com.senai.Atividade_Pontuada.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> listar(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("funcionarios", funcionarioService.getAllFuncionarios()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> buscarPorId(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("funcionario", funcionarioService.buscarFuncionarioPorId(id)));
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> salvar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.salvarFuncionario(funcionario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Funcionario cadastrado com sucesso"));
    }

    @PutMapping
    public ResponseEntity<Map<String,Object>> atualizar(@Valid @RequestBody Funcionario funcionario){
        funcionarioService.atualizar(funcionario);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Funcionario atualizado com sucesso"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Object>> deletar(@PathVariable Long id){
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("mensagem", "Funcionario deletado com sucesso"));
    }



}
