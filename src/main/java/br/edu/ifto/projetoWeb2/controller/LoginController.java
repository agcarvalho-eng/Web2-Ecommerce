package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.Usuario;
import br.edu.ifto.projetoWeb2.model.repository.LoginRepository;
import br.edu.ifto.projetoWeb2.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Transactional
@Controller
public class LoginController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("autenticacao/login");
    }

    @GetMapping("/CadastroUsuario")
    public ModelAndView cadastroUsuario(Usuario usuario, ModelMap model) {
        model.addAttribute("usuario", usuario);
        return new ModelAndView("autenticacao/cadastrarUsuario");
    }

    @PostMapping("/persistirNovoUsuario")
    public String cadastrarNovoUsuario(@ModelAttribute("usuario") @Valid Usuario usuario,
        BindingResult result, ModelMap model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "autenticacao/cadastrarUsuario";
        }
        usuarioRepository.save(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "redirect:/login";
    }
}
