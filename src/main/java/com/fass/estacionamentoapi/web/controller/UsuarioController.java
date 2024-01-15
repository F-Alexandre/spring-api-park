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

      @GetMapping("/{id}")
      public ResponseEntity<UsuarioResponseDTO> createId(@PathVariable Long id){
          Usuario usuario = usuarioService.buscarPorId(id);
          return ResponseEntity.ok(UsuarioMapper.toDTO(usuario));

      }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> createPatch(@PathVariable Long id ,@Valid @RequestBody UsuarioSenhaDTO usuarioSenhaDTO){
        Usuario usuario1 = usuarioService.editarSenha(id,usuarioSenhaDTO.getSenhaAtual(),usuarioSenhaDTO.getNewPassword(),usuarioSenhaDTO.getConfirmPassword());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll(){
        List<Usuario> usuario = usuarioService.buscarPorTodos();
        return ResponseEntity.ok(UsuarioMapper.getAllDto(usuario));

    }

    @DeleteMapping("/{d}")
    public void delete(@PathVariable("{id}") Long id){
       usuarioService.deleteId(id);
      }

}
