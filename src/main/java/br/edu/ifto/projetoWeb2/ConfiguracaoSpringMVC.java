package br.edu.ifto.projetoWeb2;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration para indicar ao Spring que essa é uma classe de configuração.
 * Em seguida, é preciso implementar a interface WebMvcConfigurer.
 * @author fagno
 */
@Configuration
public class ConfiguracaoSpringMVC implements WebMvcConfigurer {


    /**
     * Com a chamada a registry.addViewController(), estamos registrando um controller
     * definido pelo próprio Spring, para atender a requisições direcionadas à URL / e /home. E com a chamada
     * a setViewName(), sempre que a aplicação receber uma requisição para um desses endereços, a view home,
     * criada na última aula, será exibida.
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "/venda/listVendas");
        registry.addViewController("/produto/").setViewName("redirect:/produto/list");
        registry.addViewController("/venda/").setViewName("forward:/venda/list");
        registry.addViewController("/home/").setViewName("forward:index.html");
        registry.addViewController("/pessoaFisica/").setViewName("redirect:/pessoaFisica/list");
        registry.addViewController("/pessoaJuridica/").setViewName("redirect:/pessoaJuridica/list");
    }
}
