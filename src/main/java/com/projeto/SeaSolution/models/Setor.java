package com.projeto.SeaSolution.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

    @Entity
    public class Setor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @NotEmpty
        private String nomeSetor;

        @OneToOne
        private Cargo cargo;

        public String getNomeSetor() {
            return nomeSetor;
        }

        public void setNomeSetor(String nomeSetor) {
            this.nomeSetor = nomeSetor;
        }

        public Cargo getCargo() {
            return cargo;
        }

        public void setCargo(Cargo cargo) {
            this.cargo = cargo;
        }
    }
