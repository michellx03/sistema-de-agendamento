package br.com.sistema.agendamento.service;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema.agendamento.domain.Paciente;
import br.com.sistema.agendamento.exception.BadResourceException;
import br.com.sistema.agendamento.exception.ResourceAlreadyExistsException;
import br.com.sistema.agendamento.exception.ResourceNotFoundException;
import br.com.sistema.agendamento.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    private boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }

    public Paciente findById(Long id) throws ResourceNotFoundException {
        Paciente pacientes = pacienteRepository.findById(id).orElse(null);
        if (pacientes==null) {
            throw new ResourceNotFoundException("Não foi possivel achar o paciente com id: " + id);
        }
        else return pacientes;
    }

    public List<Paciente> findAll(int pageNumber, int rowPerPage) {
        List<Paciente> pacientes = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber - 1, rowPerPage,
                Sort.by("id").ascending());
        pacienteRepository.findAll(sortedByIdAsc).forEach(pacientes::add);
        return pacientes;
    }

    public Paciente save(Paciente pacientes) throws BadResourceException, ResourceAlreadyExistsException {
        if (!StringUtils.isEmpty(pacientes.getNome())) {
            if (pacientes.getId() != null && existsById(pacientes.getId())) {
                throw new ResourceAlreadyExistsException("Paciente com o id: " + pacientes.getId() +
                        " já existe!S");
            }
            return pacienteRepository.save(pacientes);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar  o paciente");
            exc.addErrorMessage("O campo nome não pode ser vazio!S");
            throw exc;
        }
    }

    public void update(Paciente pacientes) throws BadResourceException, ResourceNotFoundException {
        if (!StringUtils.isEmpty(pacientes.getNome())) {
            if (!existsById(pacientes.getId())) {
                throw new ResourceNotFoundException("Não foi possivel localizar  o paciente com id: " + pacientes.getId());
            }
            pacienteRepository.save(pacientes);
        }
        else {
            BadResourceException exc = new BadResourceException("Falha ao salvar o paciente");
            exc.addErrorMessage("Nome não pode ser vaizo!");
            throw exc;
        }
    }

    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Não foi possivel localizar  o paciente com id: " + id);
        }
        else {
            pacienteRepository.deleteById(id);
        }
    }

    public Long count() {
        return pacienteRepository.count();
    }
}
