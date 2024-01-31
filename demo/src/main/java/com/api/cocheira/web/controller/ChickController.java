package com.api.cocheira.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.api.cocheira.Entity.Chick;
import com.api.cocheira.service.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Indica que é um controlador Spring MVC para lidar com requisições web
@RequiredArgsConstructor // Gera um construtor com argumentos para inicializar final fields
@RestController // Combina as anotações @Controller e @ResponseBody para retornar dados no corpo da resposta HTTP
@RequestMapping("api/v1/chick") // Define o mapeamento base para as URLs manipuladas por este controlador
public class ChickController {

    private final ChickService chickService; // Injeção de dependência do serviço

    @PostMapping // Mapeia a requisição HTTP POST para criar um novo Chick
    public ResponseEntity<Chick> create(@RequestBody Chick chick) {
        Chick pintinho = chickService.criarChick(chick); // Cria um novo Chick
        return ResponseEntity.status(HttpStatus.CREATED).body(pintinho); // Retorna o Chick criado com status 201 CREATED
    }

    @GetMapping("/{id}") // Mapeia a requisição HTTP GET para obter um Chick pelo ID
    public ResponseEntity<Chick> getById(@PathVariable Long id) {
        Chick pintinho = chickService.buscarPorId(id); // Busca um Chick pelo ID
        return ResponseEntity.ok().body(pintinho); // Retorna o Chick encontrado com status 200 OK
    }

    @PostMapping("/{id}/anilha") // Mapeia a requisição HTTP POST para adicionar uma anilha para macho a um Chick específico
    public ResponseEntity<Chick> adicionarAnilhaMacho(@PathVariable Long id, @RequestParam String numeroAnilha) {
        Chick pintinho = chickService.buscarPorId(id); // Busca o Chick pelo ID
        if (pintinho != null) {
            pintinho.adicionarAnilhaMacho(numeroAnilha); // Adiciona uma anilha para macho ao Chick
            Chick pintinhoAtualizado = chickService.atualizarPintinho(pintinho); // Atualiza o Chick no banco de dados
            return new ResponseEntity<>(pintinhoAtualizado, HttpStatus.OK); // Retorna o Chick atualizado com status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND se o Chick não for encontrado
        }
    }

    @PutMapping("/{id}/femea") // Mapeia a requisição HTTP PUT para adicionar uma anilha para fêmea a um Chick específico
    public ResponseEntity<Chick> adicionarAnilhaFemea(@PathVariable Long id, @RequestParam String numeroAnilha) {
        Chick pintinho = chickService.buscarPorId(id); // Busca o Chick pelo ID
        if (pintinho != null) {
            pintinho.adicionarAnilhaFemea(numeroAnilha); // Adiciona uma anilha para fêmea ao Chick
            Chick pintinhoAtualizado = chickService.atualizarPintinho(pintinho); // Atualiza o Chick no banco de dados
            return new ResponseEntity<>(pintinhoAtualizado, HttpStatus.OK); // Retorna o Chick atualizado com status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND se o Chick não for encontrado
        }
    }

    @PutMapping("/{id}") // Mapeia a requisição HTTP PUT para atualizar um Chick
    public ResponseEntity<Chick> atualizarPintinho(@PathVariable Long id, @RequestBody Chick pintinho) {
        pintinho.setId(id); // Garante que o ID do pintinho a ser atualizado é o mesmo passado na URL
        Chick pintinhoAtualizado = chickService.atualizarPintinho(pintinho); // Atualiza o Chick no banco de dados
        if (pintinhoAtualizado != null) {
            return new ResponseEntity<>(pintinhoAtualizado, HttpStatus.OK); // Retorna o Chick atualizado com status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND se o Chick não for encontrado
        }
    }

    @GetMapping // Mapeia a requisição HTTP GET para listar todos os Chick
    public ResponseEntity<List<Chick>> listarPintinhos() {
        List<Chick> pintinhos = chickService.listarPintinhos(); // Obtém a lista de todos os Chick
        return new ResponseEntity<>(pintinhos, HttpStatus.OK); // Retorna a lista de Chick com status 200 OK
    }

    @DeleteMapping("/{id}") // Mapeia a requisição HTTP DELETE para excluir um Chick pelo ID
    public ResponseEntity<Void> excluirPintinho(@PathVariable Long id) {
        boolean removido = chickService.excluirPintinho(id); // Tenta excluir o Chick pelo ID
        if (removido) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 NO CONTENT se a exclusão for bem-sucedida
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 NOT FOUND se o Chick não for encontrado
        }
    }
}
