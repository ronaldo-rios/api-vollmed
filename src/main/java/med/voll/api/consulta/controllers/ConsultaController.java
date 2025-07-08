package med.voll.api.consulta.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.consulta.dto.DadosCancelamento;
import med.voll.api.consulta.dto.DetalhesAgendamentoConsulta;
import med.voll.api.consulta.service.AgendaConsultas;
import med.voll.api.consulta.service.BuscaConsulta;
import med.voll.api.consulta.service.CancelaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaConsultas agendaConsultas;
    @Autowired
    private BuscaConsulta buscaConsulta;
    @Autowired
    private CancelaConsultas cancelaConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesAgendamentoConsulta> agendar(@RequestBody @Valid DadosAgendamento dados) {
        var agendamento = agendaConsultas.agendar(dados);
        return ResponseEntity.ok(agendamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesAgendamentoConsulta> detalharConsulta(@PathVariable Long id) {
        var consulta = buscaConsulta.buscar(id);
        return ResponseEntity.ok(consulta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesAgendamentoConsulta> cancelarConsulta(@RequestBody @Valid DadosCancelamento dados) {
        var consulta = cancelaConsultas.cancelar(dados.id());
        return ResponseEntity.ok(consulta);
    }

}
