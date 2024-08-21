package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.PessoaFisica;
import br.edu.ifto.projetoWeb2.model.entity.PessoaJuridica;
import br.edu.ifto.projetoWeb2.model.entity.Role;
import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import br.edu.ifto.projetoWeb2.model.repository.*;
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
    PessoaFisicaRepository pessoaFisicaRepository;

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping()
    public ModelAndView login() {
        return new ModelAndView("/autenticacao/login"); //Direciona para a view em templates/auteticacao/login.html
    }

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario usuario) {
        return "/autenticacao/cadastrarUsuario";
    }

    @GetMapping("/cadastroUsuarioPf")
    public String cadastroUsuarioPf(Usuario usuario) {
        return "/autenticacao/cadastroUsuarioPf"; //Redirecionando para o formulário de cadastro novo usuário PF.
    }

    @GetMapping("/cadastroUsuarioPj")
    public String cadastroUsuarioPj(Usuario usuario) {
        return "/autenticacao/cadastroUsuarioPj"; //Redirecionando para o formulário de cadastro novo usuário PJ.
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

    @PostMapping("/persistirNovoUsuarioPf")
    public ModelAndView persistirNovoUsuarioPf(@Valid PessoaFisica pessoaFisica,
        BindingResult result, RedirectAttributes attributes, ModelMap model) {
        if(result.hasErrors()) {
        return new ModelAndView("/autenticacao/cadastroUsuarioPf");
        }
        Usuario usuarioPf = pessoaFisica.getUsuarioLogado();

        if(usuarioPf.getUsername().isBlank() && usuarioPf.getPassword().isBlank()) {
            attributes.addFlashAttribute("msgSenhaUsuario", "Usuário e senha não informados!");
            //model.addAttribute("pessoaFisica", pessoaFisica);
            return new ModelAndView("/autenticacao/cadastroUsuarioPf");
        }
        if(usuarioPf.getUsername().isBlank()){
            attributes.addFlashAttribute("msgUsuario", "Usuário não informado!");
            //model.addAttribute("pessoaFisica", pessoaFisica);
            return new ModelAndView("/autenticacao/cadastroUsuarioPf");
        }else if(usuarioPf.getPassword().isBlank()){
            attributes.addFlashAttribute("msgSenha", "Senha não informada!");
            //model.addAttribute("pessoaFisica", pessoaFisica);
            return new ModelAndView("/autenticacao/cadastroUsuarioPf");
        }

        usuarioPf.setPassword(new BCryptPasswordEncoder().encode(usuarioPf.getPassword()));
        Role role = roleRepository.role(2L);
        usuarioPf.getRoles().add(role);
        pessoaFisicaRepository.save(pessoaFisica);
        usuarioRepository.save(usuarioPf);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/persistirNovoUsuarioPj")
    public ModelAndView persistirNovoUsuarioPj(@Valid PessoaJuridica pessoaJuridica,
        BindingResult result, RedirectAttributes attributes, ModelMap model) {
        if(result.hasErrors()) {
            return new ModelAndView("/autenticacao/cadastroUsuarioPj");
        }
        Usuario usuarioPj = pessoaJuridica.getUsuarioLogado();

        if(usuarioPj.getUsername().isBlank() && usuarioPj.getPassword().isBlank()) {
            attributes.addFlashAttribute("msgSenhaUsuario", "Usuário e senha não informados!");
            return new ModelAndView("/autenticacao/cadastroUsuarioPj");
        }

        if(usuarioPj.getUsername().isBlank()){
            attributes.addFlashAttribute("msgUsuario", "Usuário não informado");
            return new ModelAndView("/autenticacao/cadastroUsuarioPj");
        }else if(usuarioPj.getPassword().isBlank()){
            attributes.addFlashAttribute("msgSenha", "Senha não informada!");
            return new ModelAndView("/autenticacao/cadastroUsuarioPj");
        }

        usuarioPj.setPassword(new BCryptPasswordEncoder().encode(usuarioPj.getPassword()));
        Role role = roleRepository.role(2L);
        usuarioPj.getRoles().add(role);
        pessoaJuridicaRepository.save(pessoaJuridica);
        usuarioRepository.save(usuarioPj);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return new ModelAndView("redirect:/login");
    }
}

