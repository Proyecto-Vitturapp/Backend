package com.vittur.vitturapp.repository;

import com.vittur.vitturapp.model.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionRepository extends JpaRepository<Revision, Integer> {
    List<Revision> findByVehiculoMatricula(String matricula);
}
