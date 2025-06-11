package com.luizgmelo.sistema_ponto_eletronico.services;

import com.luizgmelo.sistema_ponto_eletronico.dtos.UserDto;
import com.luizgmelo.sistema_ponto_eletronico.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<UserDto> getAllUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return userRepository.findAll(pageable)
                .map(u -> new UserDto(u.getId(), u.getName(), u.getCpf(), u.getIsActive(), u.getIsManagement(), u.getSector().getId()));
    }
}
