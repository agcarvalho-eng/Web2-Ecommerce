package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.PessoaFisica;
import br.edu.ifto.projetoWeb2.model.repository.PessoaFisicaRepository;
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
    public ModelAndView save(@Valid PessoaFisica pessoaFisica, BindingResult result,
                             RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return new ModelAndView("/pessoa-fisica/form");
        }
        pessoaFisicaRepository.save(pessoaFisica);
        attributes.addFlashAttribute("mensagem", "Usuário cadastrado com sucesso!");
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

    @GetMapping("/buscarNomePF")
    public ModelAndView buscarNomePF(@RequestParam("nome") String nome, ModelMap model){
        if(nome.isBlank()){
            return new ModelAndView("redirect:/pessoaFisica/list");
        }
        model.addAttribute("pessoasFisicas", pessoaFisicaRepository.buscarNomePF(nome.trim()));
        return new ModelAndView("/pessoa-fisica/list", model); //Aponta o caminho da view no projeto em /templates/pessoa-fisica.
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaFisica pessoaFisica, BindingResult result) {
        if(result.hasErrors()) {
            return new ModelAndView("/pessoa-fisica/form");
        }
        pessoaFisicaRepository.update(pessoaFisica);
        return new ModelAndView("redirect:/pessoaFisica/list");
    }
}
