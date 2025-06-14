package med.voll.api.medico.controllers;

import jakarta.validation.Valid;
import med.voll.api.medico.dto.DadosAtualizaMedico;
import med.voll.api.medico.dto.DetalheMedico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.ListagemMedicosDto;
import med.voll.api.medico.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrarMedico(
            @RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder
    ) {
        var medico = new Medico(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId());
        medicoRepository.save(medico);
        return ResponseEntity
                .created(uri.toUri())
                .body(medico);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicosDto>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        var page = medicoRepository.findAllIdNomeCrmEspecialidadeTelefone(pageable);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalheMedico> atualizarMedico(@RequestBody @Valid DadosAtualizaMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.update(dados);
        return ResponseEntity.ok(new DetalheMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Medico> removerMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheMedico> detalharMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalheMedico(medico));
    }
}
