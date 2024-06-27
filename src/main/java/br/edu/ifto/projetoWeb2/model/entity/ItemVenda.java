package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Entity
public class ItemVenda implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Positive
    @Min(1)
    private double quantidade;
    /*
     * @ManyToOne - Usada para mapear um relacionamento
     * muitos-para-um entre duas entidades em um contexto de
     * mapeamento objeto-relacional (ORM). Essa anotação indica
     * que a entidade anotada possui uma associação para outra
     * entidade e que a outra entidade pode ter várias instâncias
     * associadas a ela. Quando se anota um campo com @ManyToOne,
     * se está indicando que esse campo representa o lado "muitos"
     * do relacionamento.
     */
    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Venda venda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    // Método para calcular o valor total da lista ItemVenda.
    public double totalItemVenda() {
        return quantidade * produto.getValor();
    }
}
