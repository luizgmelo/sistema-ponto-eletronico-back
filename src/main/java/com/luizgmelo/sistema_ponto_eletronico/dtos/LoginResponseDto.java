package com.luizgmelo.sistema_ponto_eletronico.dtos;

public record LoginResponseDto(String token, boolean management, String name, String cpf) {
}
