package med.voll.api.consulta.controllers;

import jakarta.validation.Valid;
import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.consulta.dto.DetalhesAgendamentoConsulta;
import med.voll.api.consulta.service.AgendaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaConsultas agendaConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados) {
        var agendamento = agendaConsultas.agendar(dados);
        return ResponseEntity.ok(agendamento);
    }

}
