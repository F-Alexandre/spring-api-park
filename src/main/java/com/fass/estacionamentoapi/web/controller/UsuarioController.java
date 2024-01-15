package com.fass.estacionamentoapi.web.controller;


import com.fass.estacionamentoapi.entity.Usuario;
import com.fass.estacionamentoapi.exceptions.ErrorMessage;
import com.fass.estacionamentoapi.service.UsuarioService;
import com.fass.estacionamentoapi.web.controller.dto.UsuarioCreateDTO;
import com.fass.estacionamentoapi.web.controller.dto.UsuarioResponseDTO;
import com.fass.estacionamentoapi.web.controller.dto.UsuarioSenhaDTO;
import com.fass.estacionamentoapi.web.controller.dto.mapper.UsuarioMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "Usuarios", description = "contem todas as operacoes")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {


      private final UsuarioService usuarioService;

      @PostMapping @Operation( summary = "Criar um novo elemento" , description = "recurso para criar um novo recurso" , responses = {
              @ApiResponse(responseCode = "201" , description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDTO.class))),

              @ApiResponse(responseCode = "409" , description = "Usuario e-mail ja cadastrado no sistema", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),

              @ApiResponse(responseCode = "422" , description = "Recurso nao processado", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),


      })
      public ResponseEntity<UsuarioResponseDTO> createPost(@Valid  @RequestBody UsuarioCreateDTO usuarioCreateDTO){
            Usuario usuario1 = usuarioService.salvar(UsuarioMapper.toUsario(usuarioCreateDTO));
             return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDTO(usuario1));
      }


      @GetMapping("/{id}") @Operation( summary = "Recuperar um usuario pelo id" , description = "Recupera um usuario pelo id" , responses = {
              @ApiResponse(responseCode = "200" , description = "Recurso recuperado com sucesso", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDTO.class))),
              @ApiResponse(responseCode = "404" , description = "Recurso nao encontrado", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),
      })
      public ResponseEntity<UsuarioResponseDTO> createId(@PathVariable Long id){
          Usuario usuario = usuarioService.buscarPorId(id);
          return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));

      }

    @PatchMapping("/{id}") @Operation( summary = "Atualizar senha" , description = "recurso para atualizar senha" , responses = {
            @ApiResponse(responseCode = "204" , description = "Senha atualizada com sucesso", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDTO.class))),

            @ApiResponse(responseCode = "404" , description = "Senha nao confere", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = Void.class))),

            @ApiResponse(responseCode = "400" , description = "Recurso nao encontrado ", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),

    })
    public ResponseEntity<Void> createPatch(@PathVariable Long id ,@Valid @RequestBody UsuarioSenhaDTO usuarioSenhaDTO){
        Usuario usuario1 = usuarioService.editarSenha(id,usuarioSenhaDTO.getSenhaAtual(),usuarioSenhaDTO.getNewPassword(),usuarioSenhaDTO.getConfirmPassword());
        return ResponseEntity.noContent().build();

    }

    @GetMapping @Operation( summary = "Recurso para listar todos os usuarios" , description = "Recurso para listar todos os usuarios" , responses = {
            @ApiResponse(responseCode = "200" , description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404" , description = "nao ha  cadastrado no sistema", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),

        //    @ApiResponse(responseCode = "422" , description = "Recurso nao processado", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),
    })
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        List<Usuario> usuario = usuarioService.buscarPorTodos();
        return ResponseEntity.ok(UsuarioMapper.getAllDto(usuario));

    }

    @DeleteMapping("/{d}") @Operation( summary = "Recurso para excluir um usuario" , description = "RRecurso para excluir um usuario" , responses = {
            @ApiResponse(responseCode = "200" , description = "Recurso excluido com sucesso", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404" , description = "nao ha  cadastrado no sistema", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),

            //    @ApiResponse(responseCode = "422" , description = "Recurso nao processado", content = @Content(mediaType = "application/json" , schema = @Schema(implementation = ErrorMessage.class))),
    })
    public void delete(@PathVariable("{id}") Long id){
       usuarioService.deleteId(id);
      }

}
