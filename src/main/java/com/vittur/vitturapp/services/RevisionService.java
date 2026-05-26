package com.vittur.vitturapp.services;

import com.vittur.vitturapp.dtos.RevisionDTO;
import com.vittur.vitturapp.dtos.create.RevisionCreateDTO;
import com.vittur.vitturapp.model.Revision;
import com.vittur.vitturapp.repository.RevisionRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class RevisionService {

    private final RevisionRepository revisionRepository;

    public List<Revision> getAllRevisiones() {
        return revisionRepository.findAll();
    }

    public void save(Revision revision){
        revisionRepository.save(revision);
    }

    public Revision getRevisionById(Integer id){
        return revisionRepository.findById(id).get();
    }

    public void deleteRevision(Integer id){
        revisionRepository.deleteById(id);
    }

    public List<Revision> getRevisionesByMatricula(String matricula){
        return revisionRepository.findByVehiculoMatricula(matricula);
    }
}
