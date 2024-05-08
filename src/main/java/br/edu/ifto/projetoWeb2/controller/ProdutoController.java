package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.repository.ProdutoRepository;
import br.edu.ifto.projetoWeb2.model.entity.Produto;
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
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;

    /**
     * @param produto necessário devido utilizar no form.html o th:object que faz referência ao objeto esperado no controller.
     * @return
     */
    @GetMapping("/form")
    public String form(Produto produto, ModelMap model){
        model.addAttribute("produto",produto);
        return "/produto/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("msg", "Lista de Produtos");
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/produto/list", model); //Aponta o caminho da view no projeto em /templates/produto.
    }

    @GetMapping("/list-vitrine")
    public ModelAndView listarVitrine(ModelMap model) {
        model.addAttribute("msg", "Lista de Produtos");
        model.addAttribute("produtos", repository.produtos());
        return new ModelAndView("/produto/vitrine", model); //Aponta o caminho da view no projeto em /templates/produto.
    }

    @PostMapping("/save")
    public ModelAndView save(Produto produto) {
        repository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL.
     */
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        repository.remove(id);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
    /**
     * @param id
     * @return
     * @PathVariable é utilizado quando o valor da variável é passada diretamente na URL
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", repository.produto(id));
        return new ModelAndView("/produto/form", model); // Aponta o caminho da view no projeto em /templates/produto).
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        repository.update(produto);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
}
