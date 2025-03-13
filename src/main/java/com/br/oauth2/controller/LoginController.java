package com.br.oauth2.controller;

import com.br.oauth2.models.dtos.LoginRequestDTO;
import com.br.oauth2.models.dtos.LoginResponseDTO;
import com.br.oauth2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO obj = service.login(loginRequestDTO);
        return ResponseEntity.ok().body(obj);
    }
}
