package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {
    @PersistenceContext
    private EntityManager em;


    public Pessoa pessoa(Long id){
        return em.find(Pessoa.class, id);
    }

    public Pessoa buscarPorNomeUsuario(String username){
        TypedQuery<Pessoa> query = em.createQuery("select p from Pessoa p join Usuario  u on p.usuario.id = u.id where u.username = :username", Pessoa.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public List<Pessoa> pessoa(){
        Query query = em.createQuery("from Pessoa");
        return query.getResultList();
    }

}
