package med.voll.api.medico.controllers;

import jakarta.validation.Valid;
import med.voll.api.medico.dto.DadosAtualizaMedico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.medico.dto.DadosCadastroMedico;
import med.voll.api.medico.dto.ListagemMedicosDto;
import med.voll.api.medico.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        var medico = new Medico(dados);
        medicoRepository.save(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(medico);
    }

    @GetMapping
    public Page<ListagemMedicosDto> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return medicoRepository.findAllIdNomeCrmEspecialidadeTelefone(pageable);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizarMedico(@RequestBody @Valid DadosAtualizaMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.update(dados);
        return ResponseEntity.ok("Atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> removerMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.ok("Removido / Inativado com sucesso!");
    }
}
