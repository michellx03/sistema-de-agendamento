package br.com.sistema.agendamento.controller;

import br.com.sistema.agendamento.entities.Paciente;
import br.com.sistema.agendamento.service.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PacienteController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final int ROW_PER_PAGE = 5;

    @Autowired
    private PacienteService pacienteService;

    @Value("${msg.title}")
    private String title;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", title);
        return "index";
    }

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
        return "pacientes/paciente-list";
    }

    @GetMapping(value = "/pacientes/{pacienteId}")
    public String getPacienteById(Model model, @PathVariable long pacienteId) {
        return null;
    }

    @GetMapping(value = {"/pacientes/add"})
    public String showAddPaciente(Model model) {
        return null;
    }

    @PostMapping(value = "/pacientes/add")
    public String addPaciente(Model model, @ModelAttribute("paciente") Paciente paciente) {
        return null;
    }

    @GetMapping(value = {"/pacientes/{pacienteId}/edit"})
    public String showEditPaciente(Model model, @PathVariable long pacienteId) {
        return null;
    }

    @PostMapping(value = {"/pacientes/{pacienteId}/edit"})
    public String updatePaciente(Model model, @PathVariable long pacienteId, @ModelAttribute("paciente") Paciente paciente) {
        return null;
    }

    @GetMapping(value = {"/pacientes/{pacienteId}/delete"})
    public String showDeletePacienteById(Model model, @PathVariable long pacienteId) {
        return null;
    }

    @PostMapping(value = {"/pacientes/{pacienteId}/delete"})
    public String deletePacienteById(Model model, @PathVariable long pacienteId) {
        return null;
    }
}
