package com.vittur.vitturapp.repository;

import com.vittur.vitturapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByIdUsuario(Integer userId);
    List<Usuario> findByVehiculosMatricula(String matricula);
}
