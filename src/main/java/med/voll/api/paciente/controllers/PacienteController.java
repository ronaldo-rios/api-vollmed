package med.voll.api.paciente.controllers;

import jakarta.validation.Valid;
import med.voll.api.paciente.dto.DadosCadastroPaciente;
import med.voll.api.paciente.dto.ListagemPacientesDto;
import med.voll.api.paciente.entity.Paciente;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        var paciente = new Paciente(dados);
        pacienteRepository.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping
    public Page<ListagemPacientesDto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return pacienteRepository.findAllIdNomeTelefoneDtNascimento(pageable);
    }
}
