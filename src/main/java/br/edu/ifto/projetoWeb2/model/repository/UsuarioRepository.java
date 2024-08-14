package br.edu.ifto.projetoWeb2.model.repository;

import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;
    Usuario usuario = new Usuario();

    public Usuario loadUserByUsername(String username){
        Query query = em.createQuery("from Usuario u where u.login like :l"); //VERIFICAR AQUI SE "u.login" PODE SER USADO.
        query.setParameter("l", username);
        for (Object u: query.getResultList()) {
            usuario = (Usuario) u;
            usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        }
        return usuario;
    }

    public void save(Usuario novoUsuario) {
        em.persist(novoUsuario);
    }
}
