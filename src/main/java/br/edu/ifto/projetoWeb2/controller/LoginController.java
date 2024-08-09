package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping()
    public ModelAndView login() {

            return new ModelAndView("autenticacao/login");

    }
}
