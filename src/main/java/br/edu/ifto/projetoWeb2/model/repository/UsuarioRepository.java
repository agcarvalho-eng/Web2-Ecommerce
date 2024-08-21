package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario loadUserByUsername(String username){
        TypedQuery<Usuario> query = em.createQuery("from Usuario u where u.username = :username",Usuario.class); //VERIFICAR AQUI SE "u.login" PODE SER USADO.
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    public void save(Usuario novoUsuario) {
        em.persist(novoUsuario);
    }


}
