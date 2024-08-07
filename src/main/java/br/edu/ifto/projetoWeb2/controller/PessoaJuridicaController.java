package br.edu.ifto.projetoWeb2.controller;


import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.entity.PessoaJuridica;
import br.edu.ifto.projetoWeb2.model.repository.PessoaJuridicaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("pessoaJuridica")
public class PessoaJuridicaController {
    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    /**
     * @param pessoaJuridica necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(PessoaJuridica pessoaJuridica, ModelMap model){
        model.addAttribute("pessoaJuridica", pessoaJuridica);
        return "/pessoa-juridica/form";  // Aponta o caminho da view no projeto em /templates/pessoa-juridica).
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("msg", "Lista de Pessoas Juridicas");
        model.addAttribute("pessoasJuridicas", pessoaJuridicaRepository.pessoasJuridicas());
        return new ModelAndView("/pessoa-juridica/list", model); //Aponta o caminho da view no projeto em /templates/pessoa-juridica.
    }

    @GetMapping("/buscarNomePJ")
    public ModelAndView buscarNomePJ(@RequestParam("nome") String nome, ModelMap model){
        if(nome.isBlank()){
            return new ModelAndView("redirect:/pessoaJuridica/list");
        }
        model.addAttribute("pessoasJuridicas", pessoaJuridicaRepository.buscarNomePJ(nome.trim()));
        return new ModelAndView("/pessoa-juridica/list", model); //Aponta o caminho da view no projeto em /templates/pessoa-juridica.
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid PessoaJuridica pessoaJuridica, BindingResult result,
                             RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return new ModelAndView("/pessoa-juridica/form");
        }
        pessoaJuridicaRepository.save(pessoaJuridica);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso");
        return new ModelAndView("redirect:/pessoaJuridica/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL.
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        pessoaJuridicaRepository.remove(id);
        return new ModelAndView("redirect:/pessoaJuridica/list");
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoaJuridica", pessoaJuridicaRepository.pessoaJuridica(id));
        return new ModelAndView("/pessoa-juridica/form", model); // Aponta o caminho da view no projeto em /templates/pessoa-juridica).
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if(result.hasErrors()) {
            return new ModelAndView("/pessoa-juridica/form");
        }
        pessoaJuridicaRepository.update(pessoaJuridica);
        return new ModelAndView("redirect:/pessoaJuridica/list");
    }
}
