package med.voll.api.consulta.service;

import med.voll.api.consulta.dto.DetalhesAgendamentoConsulta;
import med.voll.api.consulta.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelaConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    public DetalhesAgendamentoConsulta cancelar(Long id) {
        var consulta = consultaRepository.getReferenceById(id);
        consulta.cancelarConsulta();
        return new DetalhesAgendamentoConsulta(consulta);
    }
}
