package com.luizgmelo.sistema_ponto_eletronico.dtos;

public record RegisterRequestDto(String name, String password, String cpf, boolean isManagement, boolean isActive) {
}
