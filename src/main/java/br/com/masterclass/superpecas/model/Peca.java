package br.com.masterclass.superpecas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Pecas")
public class Peca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pecaID;

    @Column(nullable = false, length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotNull
    @Size(min = 1, max = 255)
    private String descricao;

    @Column(nullable = false, length = 255, unique = true)
    @NotNull
    @Size(min = 1, max = 255)
    private String numeroSerie;

    @Column(nullable = false, length = 255)  
    @NotNull  
    @Size(min = 1, max = 255)
    private String fabricante;

    @Column(nullable = false, length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String modeloCarro;

    @ManyToOne // Assuming a "carro" field in Carro entity
    @JoinColumn(name = "CarroID", nullable = false)
    private Carro carro;

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Integer getPecaID() {
        return pecaID;
    }

    public void setPecaID(Integer pecaID) {
        this.pecaID = pecaID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public Carro getCarro() {
        return carro;
    }
}

