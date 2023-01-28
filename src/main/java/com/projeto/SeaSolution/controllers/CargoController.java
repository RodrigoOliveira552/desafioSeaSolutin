package com.projeto.SeaSolution.controllers;

import javax.validation.Valid;

import com.projeto.SeaSolution.models.Cargo;
import com.projeto.SeaSolution.models.Trabalhador;
import com.projeto.SeaSolution.repository.CargoRepository;
import com.projeto.SeaSolution.repository.TrabalhadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

    @Controller
    public class CargoController {

        @Autowired
        private CargoRepository cargoRepository;

        @Autowired
        private TrabalhadorRepository trabalhadorRepository;

        // CADASTRO DO CARGO
        @RequestMapping(value = "/cadastrarCargo", method = RequestMethod.GET)
        public String form() {
            return "cargo/formCargo";
        }

        @RequestMapping(value = "/cadastrarCargo", method = RequestMethod.POST)
        public String form(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {

            if (result.hasErrors()) {
                attributes.addFlashAttribute("mensagem", "Verifique os campos...");
                return "redirect:/cadastrarCargo";
            }

            cargoRepository.save(cargo);
            attributes.addFlashAttribute("mensagem", "Cargo cadastrado com sucesso!");
            return "redirect:/cadastrarCargo";
        }

        // LISTA DE CARGOS

        @RequestMapping("/cargos")
        public ModelAndView listaCargos() {
            ModelAndView mv = new ModelAndView("cargo/listaCargo");
            Iterable<Cargo> cargos = cargoRepository.findAll();
            mv.addObject("Cargos", cargos);
            return mv;
        }

        //
        @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
        public ModelAndView detalhesCargo(@PathVariable("codigo") long codigo) {
            Cargo cargo = cargoRepository.findByCodigo(codigo);
            ModelAndView mv = new ModelAndView("cargo/detalhesCargo");
            mv.addObject("cargo", cargo);

            Iterable<Trabalhador> trabalhadores = trabalhadorRepository.findByCargo(cargo);
            mv.addObject("Trabalhadores", trabalhadores);

            return mv;

        }

        // DELETA CARGOS
        @RequestMapping("/deletarCargo")
        public String deletarCargo(long codigo) {
            Cargo Cargo = cargoRepository.findByCodigo(codigo);
            cargoRepository.delete(Cargo);
            return "redirect:/Cargos";
        }

        // ADICIONA TRABALHADOR
        @RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
        public String detalhesCargoPost(@PathVariable("codigo") long codigo, @Valid Trabalhador Trabalhador,
                                       BindingResult result, RedirectAttributes attributes) {

            if (result.hasErrors()) {
                attributes.addFlashAttribute("mensagem", "Verifique os campos");
                return "redirect:/{codigo}";
            }

            // RG DUPLICADO
            if (trabalhadorRepository.findByRg(Trabalhador.getRg()) != null) {
                attributes.addFlashAttribute("mensagem_erro", "RG duplicado");
                return "redirect:/{codigo}";
            }

            //REGISTRO DE TRABALHADOR
            Cargo cargo = cargoRepository.findByCodigo(codigo);
            Trabalhador.setCargo(cargo);
            trabalhadorRepository.save(Trabalhador);
            attributes.addFlashAttribute("mensagem", "Trabalhador adicionado com sucesso!");
            return "redirect:/{codigo}";
        }

        // DELETA TRABALHADOR PELO RG
        @RequestMapping("/deletarTrabalhador")
        public String deletarTrabalhador(String rg) {
            Trabalhador trabalhador = trabalhadorRepository.findByRg(rg);
            Cargo cargo = trabalhador.getCargo();
            String codigo = "" + cargo.getCodigo();

            trabalhadorRepository.delete(trabalhador);

            return "redirect:/" + codigo;

        }

        // MÉTODOS QUE ATUALIZAM/EDITAM O CARGO E FORM DE EDIÇÃO
        @RequestMapping(value = "/editar-Cargo", method = RequestMethod.GET)
        public ModelAndView editarCargo(long codigo) {
            Cargo cargo = cargoRepository.findByCodigo(codigo);
            ModelAndView mv = new ModelAndView("cargo/update-cargo");
            mv.addObject("cargo", cargo);
            return mv;
        }

        // UPDATE CARGO
        @RequestMapping(value = "/editar-cargo", method = RequestMethod.POST)
        public String updateCargo(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {
            cargoRepository.save(cargo);
            attributes.addFlashAttribute("success", "Cargo alterada com sucesso!");

            long codigoLong = cargo.getCodigo();
            String codigo = "" + codigoLong;
            return "redirect:/" + codigo;
        }

    }

