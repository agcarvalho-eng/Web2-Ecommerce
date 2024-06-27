package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaFisica extends Pessoa {

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
