package com.javaclass.transacao_api.controller;

import com.javaclass.transacao_api.business.services.EstatisticasService;
import com.javaclass.transacao_api.controller.dtos.EstatisticasResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar estatisticas transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro Interno do servidor")

    })
    public ResponseEntity<EstatisticasResponseDto> buscarEstatisticas(
            @RequestParam(value = "deltaBusca", required = false, defaultValue = "60") Integer deltaBusca){

        return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(deltaBusca));
    }
}
