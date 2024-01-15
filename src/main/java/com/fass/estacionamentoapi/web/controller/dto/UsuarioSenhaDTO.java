package com.fass.estacionamentoapi.web.controller.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UsuarioSenhaDTO {


    @NotBlank
    @Size(min = 6 , max = 6)
    private String senhaAtual;

    @NotBlank
    @Size(min = 6 , max = 6)
    private String newPassword;

    @NotBlank
    @Size(min = 6 , max = 6)
    private String confirmPassword;


}
