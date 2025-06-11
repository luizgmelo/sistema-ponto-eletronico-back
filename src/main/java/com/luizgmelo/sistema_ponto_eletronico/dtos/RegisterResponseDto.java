package com.luizgmelo.sistema_ponto_eletronico.dtos;

public record RegisterResponseDto(Long id, String name, String cpf, boolean management, boolean isActive) {
}
