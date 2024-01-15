package com.fass.estacionamentoapi.entity;


import com.fass.estacionamentoapi.enumeracoes.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuarios") //colecao de entidades
public class Usuario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;


    @Column(name = "nome" , nullable = false, unique = true,length = 100)
    private String usuarios;


    @Column(name= "senha", nullable = false, length = 200)
    private String password;

    @Column(name= "regra" , nullable = false , length = 25)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CLIENT;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificao")
    private LocalDateTime dataModificacao;

    @Column(name =  "criado_por")
    private String criadoPor;

    @Column(name =  "modificado_por")
    private String modificadoPor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                '}';
    }
}
