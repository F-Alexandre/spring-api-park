package com.fass.estacionamentoapi.web.controller.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioCreateDTO {

    @NotBlank
    @Email(message = "email invalido", regexp = "^[a-z0-9.+-]+@[a -z0-9.-]+\\.[a-z]{2,}$")
    private String usuarios;

    @NotBlank
    @Size(min = 6 , max = 6)
    private String password;

}
