package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class PessoaJuridica extends Pessoa {

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
