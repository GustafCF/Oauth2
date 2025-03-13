package com.br.oauth2.models.dtos;

public record LoginResponseDTO(String accessToken, Long expiresIn) {
}
