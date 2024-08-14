package br.edu.ifto.projetoWeb2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import br.edu.ifto.projetoWeb2.model.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fagno
 */
@Transactional
@Repository
class UsuarioDetailsConfig implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.loadUserByUsername(login);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }

}
