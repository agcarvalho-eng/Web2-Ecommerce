package br.edu.ifto.projetoWeb2.security;

import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import br.edu.ifto.projetoWeb2.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UsuarioDetailsConfig implements UserDetailsService{


        @Autowired
        UsuarioRepository repository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = repository.loadUserByUsername(username);
            if(usuario == null){
                throw new UsernameNotFoundException("Usuário não encontrado!");
            }
            return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
        }

    }




