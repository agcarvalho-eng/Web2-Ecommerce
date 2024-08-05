package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password, ModelMap model) {
        boolean usuarioValido = loginRepository.validarUsuario(username, password);

        if (usuarioValido) {
            // Encaminhando para a página Home (index.html).
            return new ModelAndView("forward:/home");
        } else {
            // Retornando para a tela de login com erro.
            model.addAttribute("error", "erro");
            return new ModelAndView("forward:/login.html", model);
        }
    }
}
