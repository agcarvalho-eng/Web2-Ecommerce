package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

/*
 * @Entity - Informando que esta classe representa
 * uma entidade e que seus objetos devem ser persistidos
 * no banco de dados.
 *
 * @Inheritance - Usada em conjunto com a anotação @Entity
 * para especificar a estratégia de herança a ser utilizada
 * em uma hierarquia de classes que está sendo
 * mapeada para tabelas em um banco de dados relacional.
 *
 * strategy = InheritanceType.TABLE_PER_CLASS - Indica que a
 * estratégia de herança é "tabela por classe". Isso significa
 * que cada classe na hierarquia de herança será mapeada para uma
 * tabela separada no banco de dados.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
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
    * generator - Usada na anotação @GeneratedValue para
    * especificar o nome ou a referência ao gerador que será usado para
    * gerar os valores da chave primária (neste caso "inc").
    *
    * @GenericGenerator - Usada em conjunto com a anotação
    * @GeneratedValue para fornecer configurações personalizadas para
    * a geração de valores da chave primária em um contexto de mapeamento
    * objeto-relacional (ORM). Ela permite definir um gerador personalizado
    * para a geração de chaves primárias.
    *
    * name = "inc", strategy = "increment" - Definindo um gerador
    * chamado "inc" com a estratégia de incremento ("increment"). Isso significa
    * que o valor da chave primária será incrementado a cada nova inserção no
    * banco de dados.
     */
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private Long id;
    private String nome;
    private String telefone;

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    @OneToMany(mappedBy = "pessoa")
    private List<Venda> vendas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
