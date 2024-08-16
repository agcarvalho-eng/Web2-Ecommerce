package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.Role;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import br.edu.ifto.projetoWeb2.model.repository.LoginRepository;
import br.edu.ifto.projetoWeb2.model.repository.RoleRepository;
import br.edu.ifto.projetoWeb2.model.repository.UsuarioRepository;
import br.edu.ifto.projetoWeb2.security.SecurityConfiguration;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Transactional
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping()
    public ModelAndView login() {
        return new ModelAndView("/autenticacao/login"); //Direciona para a view em templates/auteticacao/login.html
    }

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario usuario, ModelMap model) {
        return "/autenticacao/cadastrarUsuario";
    }

    @PostMapping("/persistirNovoUsuario")
    public ModelAndView cadastrarNovoUsuario(@Valid Usuario usuario,
        BindingResult result, RedirectAttributes attributes) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        if (result.hasErrors()) {
            return new ModelAndView("/autenticacao/cadastrarUsuario");
        }
        Role role = roleRepository.role(2L);
        usuario.getRoles().add(role);
        usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }
}
