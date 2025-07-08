package med.voll.api.consulta.service;

import med.voll.api.consulta.dto.DetalhesAgendamentoConsulta;
import med.voll.api.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    public DetalhesAgendamentoConsulta buscar(Long id) {
        var agendamento = consultaRepository.getReferenceById(id);
        return new DetalhesAgendamentoConsulta(agendamento);
    }
}
