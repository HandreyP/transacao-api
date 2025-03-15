package com.javaclass.transacao_api.business.services;

import com.javaclass.transacao_api.controller.dtos.EstatisticasResponseDto;
import com.javaclass.transacao_api.controller.dtos.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDto calcularEstatisticasTransacoes(Integer deltaBusca) {
        log.info("Iniciada a busca de estatísticas de transacoes pelo intervalo de tempo: " + deltaBusca);
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(deltaBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor).summaryStatistics();

        if (transacoes.isEmpty()){
            return new EstatisticasResponseDto(0L,0.0,0.0,0.0, 0.0);
        }

        log.info("Estatísticas retornadas com Sucesso");
        return new EstatisticasResponseDto(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
