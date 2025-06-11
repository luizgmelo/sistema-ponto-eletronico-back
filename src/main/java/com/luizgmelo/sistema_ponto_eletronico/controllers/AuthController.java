package com.luizgmelo.sistema_ponto_eletronico.controllers;

import com.luizgmelo.sistema_ponto_eletronico.dtos.LoginRequestDto;
import com.luizgmelo.sistema_ponto_eletronico.dtos.LoginResponseDto;
import com.luizgmelo.sistema_ponto_eletronico.dtos.RegisterRequestDto;
import com.luizgmelo.sistema_ponto_eletronico.dtos.RegisterResponseDto;
import com.luizgmelo.sistema_ponto_eletronico.infra.security.TokenService;
import com.luizgmelo.sistema_ponto_eletronico.models.User;
import com.luizgmelo.sistema_ponto_eletronico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto body) {
        User user = userRepository.findByCpf(body.cpf()).orElseThrow(()-> new RuntimeException("Login ou Senha incorreto"));
        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        String token = tokenService.generateToken(user);
        LoginResponseDto response = new LoginResponseDto(token, user.getIsManagement(), user.getName(), user.getCpf());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterRequestDto body) {
        Optional<User> user = userRepository.findByCpf(body.cpf());
        if (user.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        String passwordHashed = passwordEncoder.encode(body.password());
        User newUser = new User();
        newUser.setName(body.name());
        newUser.setCpf(body.cpf());
        newUser.setPassword(passwordHashed);
        newUser.setIsActive(body.isActive());
        newUser.setIsManagement(body.isManagement());
        userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);
        RegisterResponseDto response = new RegisterResponseDto(newUser.getId(), newUser.getName(), newUser.getCpf(), newUser.getIsManagement(), newUser.getIsActive());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
