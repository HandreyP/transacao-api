package com.javaclass.transacao_api.business.services;

import com.javaclass.transacao_api.controller.dtos.TransacaoRequestDto;
import com.javaclass.transacao_api.infrastucure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDto> listarTransacao = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDto transacaoDto){

        log.info("Inicio de gravação de transações " + transacaoDto);

        if(transacaoDto.DataHora().isAfter(OffsetDateTime.now())) {
            String message = "Data e hora maior que a hora atual";

            log.error(message);
            throw new UnprocessableEntity(message);
        }

        if(transacaoDto.valor() < 0) {
            String message = "Valor menor que zero !";

            log.error(message);
            throw new UnprocessableEntity(message);
        }

        listarTransacao.add(transacaoDto);
        log.info("Transacoes adicionadas com Sucesso");
    }

    public void limparTransacao(){
        log.info("Iniciado método para eliminar as trasacoes !");
        listarTransacao.clear();
        log.info("Transacoes deletadas com Sucesso");
    }

    public List<TransacaoRequestDto> buscarTransacoes(Integer deltaBusca) {
        log.info("Iniciado método para buscar as trasacoes");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(deltaBusca);


        log.info("Retorno de transacoes com Sucesso");
        return listarTransacao.stream().filter(transacao -> transacao.DataHora()
                .isAfter(dataHoraIntervalo)).toList();
    }
}
