package br.edu.ifto.projetoWeb2.controller;


import br.edu.ifto.projetoWeb2.model.entity.PessoaJuridica;
import br.edu.ifto.projetoWeb2.model.repository.PessoaJuridicaRepository;
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
    @PostMapping("/save")
    public ModelAndView save(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.save(pessoaJuridica);
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
    public ModelAndView update(PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.update(pessoaJuridica);
        return new ModelAndView("redirect:/pessoaJuridica/list");
    }
}
