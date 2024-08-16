package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Produto;
import br.edu.ifto.projetoWeb2.model.entity.Role;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

    @PersistenceContext
    private EntityManager em;

    public Role role(Long id){
        return em.find(Role.class, id);
    }


    public void save(Role role) {
        em.persist(role);
    }
}
