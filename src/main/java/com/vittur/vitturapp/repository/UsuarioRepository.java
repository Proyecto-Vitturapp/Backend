package com.vittur.vitturapp.repository;

import com.vittur.vitturapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByIdUsuario(Integer userId);
    List<Usuario> findByVehiculosMatricula(String matricula);
    Optional<Usuario> findByUsername(String username);
}
