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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(
            @RequestBody @Valid DadosCadastroPaciente dados,
            UriComponentsBuilder uriBuilder
    ) {
        var paciente = new Paciente(dados);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId());
        pacienteRepository.save(paciente);
        return ResponseEntity.created(uri.toUri()).body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacientesDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = pacienteRepository.findAllIdNomeTelefoneDtNascimento(pageable);
        return ResponseEntity.ok(page);
    }
}
