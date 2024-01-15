package com.fass.estacionamentoapi.service;

import com.fass.estacionamentoapi.entity.Usuario;
import com.fass.estacionamentoapi.exceptions.EntityNotFoundExceptionCustom;
import com.fass.estacionamentoapi.exceptions.PasswordNotEqualsExceptions;
import com.fass.estacionamentoapi.exceptions.UsernameUniqueViolationExceptions;
import com.fass.estacionamentoapi.repository.UsuarioRepositorio;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {


   private final UsuarioRepositorio usuarioRepositorio;


   @Transactional
   public Usuario salvar(Usuario usuario){
       try {
           return usuarioRepositorio.save(usuario);

       }catch (DataIntegrityViolationException e){

           throw new UsernameUniqueViolationExceptions(String.format("Username Ja cadastrado" , usuario.getUsuarios()));
       }
       }

   @Transactional(readOnly = true)
   public Usuario buscarPorId(Long id){
       return usuarioRepositorio.findById(id).orElseThrow(()-> new EntityNotFoundExceptionCustom(String.format("Id Nao Encontrado",id)));
   }

    @Transactional
    public Usuario editarSenha(Long id , String senhaA, String newPassword, String comfirmPassword) {
        if (!newPassword.equals(comfirmPassword)) {
            throw new PasswordNotEqualsExceptions(String.format("Senhas nao sao iguais"));
        }

        Usuario usuarioId = buscarPorId(id);
        if (!usuarioId.getPassword().equals(senhaA)) {
            throw new PasswordNotEqualsExceptions(String.format("Senhas nao confere"));        }
             usuarioId.setPassword(newPassword);

        return usuarioId;
   }

    public void deleteId(Long id) {
       usuarioRepositorio.deleteById(id);

    }
    @Transactional
    public List<Usuario> buscarPorTodos() {

        return usuarioRepositorio.findAll();
   }
}

