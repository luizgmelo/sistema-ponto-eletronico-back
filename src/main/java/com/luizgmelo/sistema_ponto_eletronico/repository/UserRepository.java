package com.luizgmelo.sistema_ponto_eletronico.repository;

import com.luizgmelo.sistema_ponto_eletronico.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByCpf(String cpf);
}
