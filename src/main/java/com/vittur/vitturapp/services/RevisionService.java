package com.vittur.vitturapp.services;

import com.vittur.vitturapp.dtos.create.RevisionCreateDTO;
import com.vittur.vitturapp.model.Revision;
import com.vittur.vitturapp.repository.RevisionRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RevisionService {
    @Autowired
    private RevisionRepository revisionRepository;

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
}
