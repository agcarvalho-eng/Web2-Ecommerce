package br.edu.ifto.projetoWeb2.model.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component // Bean gerenciável pelo Spring.
@Scope("session") // A venda vai ser criada na sessão.
@Entity
public class Venda implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime dataEHorario;
/* @OneToMany - Usada para mapear um relacionamento de
 * um-para-muitos entre duas entidades em um contexto de mapeamento
 * objeto-relacional (ORM), onde uma entidade possui uma coleção de
 * outras entidades relacionadas.
 *
 * mappedBy = "venda" - A parte mappedBy especifica o nome do campo na
 * entidade relacionada que mapeia o relacionamento. Isso indica que
 * o mapeamento do relacionamento é bidirecional e que o lado inverso
 * do relacionamento é responsável pelo mapeamento do banco de dados.
 * Em outras palavras, a entidade relacionada possui a chave estrangeira
 * que referencia a entidade atual.
 */
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVenda> itensVenda = new ArrayList<>();

    @ManyToOne
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataEHorario() {
        return dataEHorario;
    }

    public void setDataEHorario(LocalDateTime dataEHorario) {
        this.dataEHorario = dataEHorario;
    }

    public List<ItemVenda> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public double totalVenda(){
        return itensVenda.stream()
                .mapToDouble(ItemVenda::totalItemVenda)
                .sum();
        /*double totalVenda = 0;
        for(ItemVenda itemVenda : itensVenda){
            totalVenda += itemVenda.totalItemVenda();
        }*/
        //return totalVenda;
    }


}
