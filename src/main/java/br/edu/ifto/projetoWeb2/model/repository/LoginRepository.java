package br.edu.ifto.projetoWeb2.model.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LoginRepository {
    @PersistenceContext
    private EntityManager em;

    public boolean validarUsuario(String username, String password) {
        // Primeiro verifica na tabela pessoaFisica.
        String queryPf = "SELECT COUNT(pf) FROM PessoaFisica pf WHERE pf.username = :username AND pf.password = :password";
        Long contador = (Long) em.createQuery(queryPf)
        //String queryPessoaFisica = "SELECT COUNT(pf) FROM PessoaFisica pf WHERE pf.username = :username AND pf.password = :password";
//        Long count = (Long) em.createQuery(queryPessoaFisica)
                                         .setParameter("username", username)
                                         .setParameter("password", password)
                                         .getSingleResult();
        if (contador > 0) {
            return true;
        } else {
            // Se não encontrou na tabela pessoaFisica, verifica na tabela pessoaJuridica.
            String queryPj = "SELECT COUNT(pj) FROM PessoaJuridica pj WHERE pj.username = :username AND pj.password = :password";
            contador = (Long) em.createQuery(queryPj)
                             .setParameter("username", username)
                             .setParameter("password", password)
                             .getSingleResult();
            return contador > 0;
        }
    }
}
