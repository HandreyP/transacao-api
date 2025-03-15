package com.javaclass.transacao_api.controller;

import com.javaclass.transacao_api.business.services.EstatisticasService;
import com.javaclass.transacao_api.controller.dtos.EstatisticasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    public ResponseEntity<EstatisticasResponseDto> buscarEstatisticas(
            @RequestParam(value = "deltaBusca", required = false, defaultValue = "60") Integer deltaBusca){

        return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(deltaBusca));
    }
}
