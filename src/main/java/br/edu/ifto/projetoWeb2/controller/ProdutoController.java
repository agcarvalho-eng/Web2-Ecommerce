package br.edu.ifto.projetoWeb2.controller;

import br.edu.ifto.projetoWeb2.model.entity.ItemVenda;
import br.edu.ifto.projetoWeb2.model.entity.Pessoa;
import br.edu.ifto.projetoWeb2.model.repository.ProdutoRepository;
import br.edu.ifto.projetoWeb2.model.entity.Produto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Transactional
@Controller
@RequestMapping("produto")
public class ProdutoController {
    @Autowired
    ProdutoRepository repository;
    @Autowired
    private ProdutoRepository produtoRepository;

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

    @GetMapping("/list/{descricao}")
    public ModelAndView buscarDescricaoProduto(@PathVariable("descricao") @Valid String descricao, ModelMap model, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("redirect:/produto/list");
        }
        List<Produto> attributeValue = produtoRepository.buscarDescricaoProduto(descricao);
        model.addAttribute("produtos", attributeValue);
        return new ModelAndView("/produto/list", model); //Aponta o caminho da view no projeto em /templates/pessoa-juridica.
    }

    @GetMapping("/list-vitrine")
    public ModelAndView listarVitrine(ModelMap model) {
        model.addAttribute("msg", "Lista de Produtos");
        model.addAttribute("produtos", repository.produtos());
        model.addAttribute("item", new ItemVenda());
        return new ModelAndView("/produto/vitrine", model); //Aponta o caminho da view no projeto em /templates/produto.
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/produto/form");
        }
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
    public ModelAndView update(@Valid Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("/produto/form");
        }
        repository.update(produto);
        return new ModelAndView("redirect:/produto/list"); //Aponta o caminho da view no projeto em /templates/produto.
    }
}
