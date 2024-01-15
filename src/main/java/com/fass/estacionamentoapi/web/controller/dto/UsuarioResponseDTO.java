package com.fass.estacionamentoapi.web.controller.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioResponseDTO {
    private  Long id;
    private String usuarios;
    private String role;
}
