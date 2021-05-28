package br.com.sistema.controller;

import br.com.sistema.model.Projeto;
import br.com.sistema.service.ProjetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjetoController {

    @Autowired
    ProjetoServiceImpl projetoService;

    @GetMapping("/projeto/list")
    public String list(Model model){
        model.addAttribute("projetos", projetoService.findAll());
        return "projeto/list";
    }

    @GetMapping("/projeto/add")
    public String add(Model model){
        model.addAttribute("projeto", new Projeto());
        return "projeto/add";
    }

    @GetMapping("/projeto/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("projeto", projetoService.findById(id));
        return "projeto/edit";
    }

    @PostMapping("/projeto/save")
    public String save(Projeto projeto, Model model){

        //TODO: os alunos farão o validar

        if (projetoService.save(projeto)){
            return "redirect:/projeto/list";
        } else {
            //TODO: os alunos farão a validação
            return "redirect:/projeto/list";
        }
    }

    @GetMapping("/projeto/delete/{id}")
    public String delete(@PathVariable Long id){
        projetoService.deleteById(id);
        return "redirect:/projeto/list";
    }
}
