package com.fass.estacionamentoapi.web.controller.dto.mapper;

import com.fass.estacionamentoapi.entity.Usuario;
import com.fass.estacionamentoapi.web.controller.dto.UsuarioCreateDTO;
import com.fass.estacionamentoapi.web.controller.dto.UsuarioResponseDTO;
import org.hibernate.mapping.Property;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsario(UsuarioCreateDTO createDTO){
        return new ModelMapper().map(createDTO , Usuario.class);
    };


    public static UsuarioResponseDTO toDTO(Usuario usuario){

//        String role = usuario.getRole().name().substring("ROLE_".length());
//
//        PropertyMap<Usuario , UsuarioResponseDTO> props = new PropertyMap<Usuario, UsuarioResponseDTO>() {
//            @Override
//            protected void configure() {
//                map().setRole(role);
//            }
//        };
        ModelMapper mapper =  new ModelMapper();
   //     mapper.addMappings(props);
      return mapper.map(usuario, UsuarioResponseDTO.class);
    };


     public static List<UsuarioResponseDTO> getAllDto(List<Usuario> usuarios){
         return usuarios.stream().map( user -> toDTO(user)).collect(Collectors.toList());
     }

}
