package com.projeto.SeaSolution.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Trabalhador {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String rg;

    @NotEmpty
    private String nomeTrabalhador;

    @NotEmpty
    private String email;

    @ManyToOne
    private Cargo cargo;

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNomeTrabalhador() {
        return nomeTrabalhador;
    }

    public void setNomeTrabalhador(String nomeTrabalhador) {
        this.nomeTrabalhador = nomeTrabalhador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
