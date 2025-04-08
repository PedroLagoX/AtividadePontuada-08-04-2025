package com.senai.Atividade_Pontuada.service;

import com.senai.Atividade_Pontuada.exception.EmailJaCadastradoException;
import com.senai.Atividade_Pontuada.model.Funcionario;
import com.senai.Atividade_Pontuada.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvarFuncionario(@Valid Funcionario funcionario) {
        if (funcionarioRepository.findByEmail(funcionario.getEmail()).isPresent()) {
            throw new EmailJaCadastradoException("Funcionario já encontrado");
        }
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        Funcionario funcionario = buscarFuncionarioPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));
        funcionarioRepository.delete(funcionario);
    }

    public Funcionario atualizar(@Valid Funcionario funcionario) {
        Funcionario funcionarioExistente = buscarFuncionarioPorId(funcionario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));
        funcionarioExistente.setName(funcionario.getName());
        funcionarioExistente.setCpf(funcionario.getCpf());
        funcionarioExistente.setDataNascimento(funcionario.getDataNascimento());
        funcionarioExistente.setSexo(funcionario.getSexo());
        funcionarioExistente.setSetor(funcionario.getSetor());
        funcionarioExistente.setEstadoCivil(funcionario.getEstadoCivil());
        funcionarioExistente.setEmail(funcionario.getEmail());
        funcionarioExistente.setEndereco(funcionario.getEndereco());


        return funcionarioRepository.save(funcionarioExistente);
    }
}

