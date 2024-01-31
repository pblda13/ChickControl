package com.api.cocheira.service;

import com.api.cocheira.Entity.Chick;
import com.api.cocheira.repository.ChickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta classe é um componente de serviço do Spring
@RequiredArgsConstructor // Cria um construtor com argumentos para inicializar final fields
public class ChickService {

    private final ChickRepository chickRepository; // Injeção de dependência do repositório

    // Método para criar um novo Chick no banco de dados
    public Chick criarChick(Chick chick) {
        return chickRepository.save(chick); // Salva o objeto Chick no banco de dados
    }

    // Método para buscar um Chick pelo ID no banco de dados
    public Chick buscarPorId(Long id) {
        Optional<Chick> optionalPintinho = chickRepository.findById(id); // Busca o Chick pelo ID

        // Retorna o Chick se encontrado, caso contrário, retorna null
        return optionalPintinho.orElse(null);
    }

    // Método para listar todos os Chick presentes no banco de dados
    public List<Chick> listarPintinhos() {
        return chickRepository.findAll(); // Retorna todos os Chick presentes no banco de dados
    }

    // Método para atualizar informações de um Chick no banco de dados
    public Chick atualizarPintinho(Chick chick) {
        Long id = chick.getId();
        Optional<Chick> optionalPintinho = chickRepository.findById(id); // Verifica se o Chick existe no banco de dados

        if (optionalPintinho.isPresent()) { // Se o Chick existir, atualiza suas informações no banco de dados
            return chickRepository.save(chick);
        } else {
            return null; // Retorna null se o Chick não for encontrado para atualização
        }
    }

    // Método para excluir um Chick do banco de dados pelo ID
    public boolean excluirPintinho(Long id) {
        if (chickRepository.existsById(id)) { // Verifica se o Chick existe no banco de dados antes de excluí-lo
            chickRepository.deleteById(id); // Exclui o Chick pelo ID
            return true; // Retorna true se a exclusão for bem-sucedida
        } else {
            return false; // Retorna false se o Chick não for encontrado para exclusão
        }
    }
}
