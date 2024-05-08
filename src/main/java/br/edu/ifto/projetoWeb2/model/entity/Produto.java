package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
/*
 * @Entity - Informando que esta classe representa
 * uma entidade e que seus objetos devem ser persistidos
 * no banco de dados.
 */
@Entity
public class Produto implements Serializable {

   /*
    * @Id - Usada para marcar um campo ou propriedade de uma entidade
    * como a chave primária (primary key) no contexto de mapeamento
    * objeto-relacional (ORM).
    *
    * @GeneratedValue - Usada em conjunto com a anotação @Id para
    * especificar como os valores da chave primária serão gerados
    * para uma entidade em um contexto de mapeamento objeto-relacional
    * (ORM). Neste caso, gerada automaticamente pelo BD.
    *
    * strategy = GenerationType.IDENTITY - Está especificando uma estratégia
    * de geração de chaves primárias chamada "IDENTITY". Está instruindo o
    * provedor de persistência a delegar a geração da chave primária para
    * o banco de dados.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private double valor;

    @ManyToOne
    private Venda venda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
