package med.voll.api.consulta.service;

import med.voll.api.consulta.dto.DadosAgendamento;
import med.voll.api.consulta.dto.DetalhesAgendamentoConsulta;
import med.voll.api.consulta.entity.Consulta;
import med.voll.api.consulta.enums.StatusConsulta;
import med.voll.api.consulta.repository.ConsultaRepository;
import med.voll.api.consulta.validations.ValidaAgendamentoConsultas;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.medico.entity.Medico;
import med.voll.api.medico.repository.MedicoRepository;
import med.voll.api.paciente.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidaAgendamentoConsultas> validadores;

    public DetalhesAgendamentoConsulta agendar(DadosAgendamento dadosAgendamento) {
        if (dadosAgendamento.idMedico() != null && !medicoRepository.existsById(dadosAgendamento.idMedico())) {
            throw new ValidacaoException("Id do médico não encontrado!");
        }

        if (!pacienteRepository.existsById(dadosAgendamento.idPaciente())) {
            throw new ValidacaoException("Id do paciente não encontrado!");
        }

        validadores.forEach(validador -> validador.validar(dadosAgendamento));

        var medico = selecionarMedico(dadosAgendamento);
        var paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());

        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        var consulta = new Consulta(null, medico, paciente, dadosAgendamento.data(), StatusConsulta.AGENDADA);
        consultaRepository.save(consulta);

        return new DetalhesAgendamentoConsulta(consulta);
    }

    private Medico selecionarMedico(DadosAgendamento dadosAgendamento) {
        if (dadosAgendamento.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosAgendamento.idMedico());
        }

        if (dadosAgendamento.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for selecionado");
        }

        return medicoRepository.buscarMedicoAleatorioLivreNaData(
            dadosAgendamento.especialidade(),
            dadosAgendamento.data()
        );
    }
}
