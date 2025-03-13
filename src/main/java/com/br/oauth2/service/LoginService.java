package com.br.oauth2.service;

import com.br.oauth2.models.RoleModel;
import com.br.oauth2.models.dtos.LoginRequestDTO;
import com.br.oauth2.models.dtos.LoginResponseDTO;
import com.br.oauth2.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private static final Logger logger = Logger.getLogger(LoginService.class.getName());

    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginService(JwtEncoder jwtEncoder, UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var user = userRepo.findByUsername(loginRequestDTO.username()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!user.loginValidation(loginRequestDTO, bCryptPasswordEncoder)){
            logger.log(Level.SEVERE, "Invalid password!");
            throw new BadCredentialsException("Invalid password!");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = user.getRoles()
                .stream()
                .map(RoleModel::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("back-end")
                .subject(user.getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDTO(jwtValue, expiresIn);
    }
}
