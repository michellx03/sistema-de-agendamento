package br.com.sistema.agendamento.controller;

import br.com.sistema.agendamento.domain.Paciente;
import br.com.sistema.agendamento.service.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PacienteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping(value = "/paciente")
    public String getPacientes(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Paciente> pacientes = pacienteService.findAll(pageNumber, ROW_PER_PAGE);

        long count = pacienteService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * ROW_PER_PAGE) < count;
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        return "paciente/paciente-list";
    }
}