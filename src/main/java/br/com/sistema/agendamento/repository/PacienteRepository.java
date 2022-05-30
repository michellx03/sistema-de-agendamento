package br.com.sistema.agendamento.repository;

import br.com.sistema.agendamento.domain.Paciente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

}
