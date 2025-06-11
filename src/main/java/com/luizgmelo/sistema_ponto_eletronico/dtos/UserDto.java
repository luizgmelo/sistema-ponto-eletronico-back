package com.luizgmelo.sistema_ponto_eletronico.dtos;

public record UserDto(Long id, String name, String cpf, boolean isActive, boolean isManagement) {
}
