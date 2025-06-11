package com.luizgmelo.sistema_ponto_eletronico.controllers;

import com.luizgmelo.sistema_ponto_eletronico.dtos.UserDto;
import com.luizgmelo.sistema_ponto_eletronico.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(@RequestParam(defaultValue = "0") int pageNo,
                                                  @RequestParam(defaultValue = "15") int pageSize) {
        return ResponseEntity.ok(userService.getAllUsers(pageNo, pageSize));
    }

}
