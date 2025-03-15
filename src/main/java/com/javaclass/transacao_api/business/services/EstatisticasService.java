package com.javaclass.transacao_api.business.services;

import com.javaclass.transacao_api.controller.dtos.EstatisticasResponseDto;
import com.javaclass.transacao_api.controller.dtos.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDto calcularEstatisticasTransacoes(Integer deltaBusca) {
        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(deltaBusca);

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor).summaryStatistics();

        return new EstatisticasResponseDto(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
