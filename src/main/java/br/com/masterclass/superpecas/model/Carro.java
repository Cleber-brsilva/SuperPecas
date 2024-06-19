package br.com.masterclass.superpecas.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carroID;

    @Column(nullable = false, length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String nomeModelo;

    @Column(nullable = false, length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String fabricante;

    @Column(nullable = false, unique = true, length = 255)
    @NotNull
    @Size(min = 1, max = 255)
    private String codigoUnico;

    public Carro() {
    }

    public Carro(String nomeModelo, String fabricante, String codigoUnico) {
        this.nomeModelo = nomeModelo;
        this.fabricante = fabricante;
        this.codigoUnico = codigoUnico;
    }

    // Getters and setters
    public Integer getCarroID() {
        return carroID;
    }

    public void setCarroID(Integer carroID) {
        this.carroID = carroID;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "carroID=" + carroID +
                ", nomeModelo='" + nomeModelo + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", codigoUnico='" + codigoUnico + '\'' +
                '}';
    }


}


