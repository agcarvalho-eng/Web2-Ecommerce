package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.PessoaFisica;
import br.edu.ifto.projetoWeb2.model.repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("pessoaFisica")
public class PessoaFisicaController {
    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

    /**
     * @param pessoaFisica necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoaFisica", pessoaFisica);
        return "/pessoa-fisica/form"; //Aponta o caminho da view no projeto em /templates/pessoa-fisica.
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("msg", "Lista de Pessoas Fisicas");
        model.addAttribute("pessoasFisicas", pessoaFisicaRepository.pessoasFisicas());
        return new ModelAndView("/pessoa-fisica/list", model); //Aponta o caminho da view no projeto em /templates/pessoa-fisica.
    }
    @PostMapping("/save")
    public ModelAndView save(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.save(pessoaFisica);
        return new ModelAndView("redirect:/pessoaFisica/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL.
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        pessoaFisicaRepository.remove(id);
        return new ModelAndView("redirect:/pessoaFisica/list");
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaFisica", pessoaFisicaRepository.pessoaFisica(id));
        return new ModelAndView("/pessoa-fisica/form", model); // Aponta o caminho da view no projeto em /templates/pessoa-fisica).
    }

    @PostMapping("/update")
    public ModelAndView update(PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoaFisica/list");
    }
}
