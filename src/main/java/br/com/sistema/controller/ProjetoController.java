package br.com.sistema.controller;

import br.com.sistema.model.Cargo;
import br.com.sistema.model.Projeto;
import br.com.sistema.service.CargoServiceImpl;
import br.com.sistema.service.FuncionarioServiceImpl;
import br.com.sistema.service.ProjetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjetoController {

    @Autowired
    ProjetoServiceImpl projetoService;

    @Autowired
    FuncionarioServiceImpl funcionarioService;

    @Autowired
    CargoServiceImpl cargoService;

    @GetMapping("/projeto/list")
    public String list(Model model){
        model.addAttribute("projetos", projetoService.findAll());
        return "projeto/list";
    }

    @GetMapping("/projeto/add")
    public String add(Model model){
        model.addAttribute("projeto", new Projeto());
        Cargo cargo = cargoService.findByNome("Gerente");
        List<Cargo> cargos = cargoService.findByNomeNot("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        model.addAttribute("funcionarios",funcionarioService.findByCargoIn(cargos));
        return "projeto/add";
    }

    @GetMapping("/projeto/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("projeto", projetoService.findById(id));
        Cargo cargo = cargoService.findByNome("Gerente");
        List<Cargo> cargos = cargoService.findByNomeNot("Gerente");
        model.addAttribute("gerentes", funcionarioService.findByCargo(cargo));
        model.addAttribute("funcionarios",funcionarioService.findByCargoIn(cargos));

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
