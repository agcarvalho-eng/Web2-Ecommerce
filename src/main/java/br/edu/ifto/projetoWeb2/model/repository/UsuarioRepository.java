package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String username){
        Query query = em.createQuery("from Usuario u where u.login like :l"); //VERIFICAR AQUI SE "u.login" PODE SER USADO.
        query.setParameter("l", username);
        //return query.getResultList();
        return (Usuario) query.getSingleResult();
    }
}
