package com.api.cocheira.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Define a classe Chick como uma entidade JPA (usada para mapeamento de objetos a bancos de dados relacionais)
@Entity
@Getter // Gera automaticamente métodos de leitura para os campos da classe
@Setter // Gera automaticamente métodos de escrita para os campos da classe
@NoArgsConstructor // Gera um construtor padrão sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos
@Table(name = "Chick") // Especifica o nome da tabela correspondente no banco de dados

public class Chick {

    @Id // Indica que o campo é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define a estratégia de geração de valores para a chave primária
    private Long id; // Identificador único do Chick

    @Column(name = "nome_reprodutor") // Especifica o nome da coluna no banco de dados
    private String nomeReprodutor; // Nome do reprodutor

    private String matriz; // Informação sobre a matriz

    @Column(name = "data_nascimento") // Especifica o nome da coluna no banco de dados
    private LocalDate dataNascimento; // Data de nascimento do Chick

    @Column(name = "quantidade_total") // Especifica o nome da coluna no banco de dados
    private int quantidadeTotal; // Quantidade total de Chick

    @Column(name = "quantidade_machos") // Especifica o nome da coluna no banco de dados
    private int quantidadeMachos; // Quantidade de machos

    @Column(name = "quantidade_femeas") // Especifica o nome da coluna no banco de dados
    private int quantidadeFemeas; // Quantidade de fêmeas

    @ElementCollection // Indica que é uma coleção de elementos a serem persistidos em uma tabela separada
    @Column(name = "anilha_macho") // Especifica o nome da coluna no banco de dados
    private List<String> anilhasMachos; // Lista de anilhas para machos

    @ElementCollection // Indica que é uma coleção de elementos a serem persistidos em uma tabela separada
    @Column(name = "anilha_femea") // Especifica o nome da coluna no banco de dados
    private List<String> anilhasFemeas; // Lista de anilhas para fêmeas

    @Column(name = "anotacoes", length = 1000) // Especifica o nome da coluna e o comprimento máximo no banco de dados
    private String anotacoes; // Anotações sobre os Chick

    // Método para adicionar uma anilha para machos à lista
    public void adicionarAnilhaMacho(String numeroAnilha) {
        if (anilhasMachos == null) {
            anilhasMachos = new ArrayList<>(); // Inicializa a lista se for nula
        }
        anilhasMachos.add(numeroAnilha); // Adiciona a anilha à lista
    }

    // Método para adicionar uma anilha para fêmeas à lista
    public void adicionarAnilhaFemea(String numeroAnilha) {
        if (anilhasFemeas == null) {
            anilhasFemeas = new ArrayList<>(); // Inicializa a lista se for nula
        }
        anilhasFemeas.add(numeroAnilha); // Adiciona a anilha à lista
    }

}
