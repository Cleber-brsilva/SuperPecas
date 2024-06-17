package br.com.masterclass.superpecas.model;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Carros")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer carroID;

    @Column(nullable = false, length = 255)
    private String nomeModelo;

    @Column(nullable = false, length = 255)
    private String fabricante;

    @Column(nullable = false, unique = true , length = 255)
    private String codigoUnico;

    @Deprecated
    public Carro(){        
    }
    private Carro(@NonNull String nomeModelo, @NonNull String fabricante) {
        setNomeModelo(nomeModelo);
        setFabricante(fabricante);
    }
    
    public Integer getCarroID() {
        return carroID;
    }
    public String getCodigoUnico() {
        return codigoUnico;
    }
    public String getFabricante() {
        return fabricante;
    }
    public String getNomeModelo() {
        return nomeModelo;
    }
    public void setCarroID(Integer carroID) {
        this.carroID = carroID;
    }
    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

}


