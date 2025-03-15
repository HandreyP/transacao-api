package com.javaclass.transacao_api.controller.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDto(Double valor, OffsetDateTime DataHora) {
}
