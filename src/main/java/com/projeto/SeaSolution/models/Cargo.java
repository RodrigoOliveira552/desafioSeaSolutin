package com.projeto.SeaSolution.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

    @Entity
    public class Cargo implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long codigo;

        @NotEmpty
        private String nome;

        @NotEmpty
        private String descricao;

        @OneToOne(mappedBy = "cargo", cascade = CascadeType.REMOVE)
        private List<Setor> setor;

        public long getCodigo() {
            return codigo;
        }

        public void setCodigo(long codigo) {
            this.codigo = codigo;
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

            public List<Setor> getSetor() {
            return setor;
        }

        public void setSetor(List<Setor> setor) {
            this.setor = this.setor;
        }
    }
