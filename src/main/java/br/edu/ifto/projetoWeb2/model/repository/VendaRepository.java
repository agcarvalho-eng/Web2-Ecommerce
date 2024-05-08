package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda){
        em.persist(venda);
    }

    public void saveCarrinho(Venda venda) {em.persist(venda);}

    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }

    public List<Venda> vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }
    public void remove(Long id){
        Venda v = em.find(Venda.class, id);
        em.remove(v);
    }

    public void update(Venda venda){
        em.merge(venda);
    }
}
