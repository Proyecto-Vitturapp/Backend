package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.RevisionDTO;
import com.vittur.vitturapp.dtos.create.RevisionCreateDTO;
import com.vittur.vitturapp.model.Revision;
import com.vittur.vitturapp.model.Vehiculo;
import com.vittur.vitturapp.services.RevisionService;
import com.vittur.vitturapp.services.UsuarioService;
import com.vittur.vitturapp.services.VehiculoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RevisionController {
    private final RevisionService revisionService;
    private final VehiculoService vehiculoService;
    private final UsuarioService usuarioService;

    @GetMapping("/reviews/all")
    public ResponseEntity<List<RevisionDTO>> getAllRevisiones(){
        try {
            List<Revision> revisiones = revisionService.getAllRevisiones();
            List<RevisionDTO> revisionesDTO = new ArrayList<>();
            for (Revision rev : revisiones) {
                revisionesDTO.add(toRevisionDTO(rev));
            }
            return new ResponseEntity<>(revisionesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<RevisionDTO> getRevisionById(@PathVariable Integer id){
        try {
            Revision revision = revisionService.getRevisionById(id);
            return new ResponseEntity<>(toRevisionDTO(revision), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{matricula}")
    public ResponseEntity<List<RevisionDTO>> getRevisionesByMatricula(@PathVariable String matricula){
        try {
            List<Revision> revisiones = revisionService.getRevisionesByMatricula(matricula);
            List<RevisionDTO> revisionesDTO = new ArrayList<>();
            for (Revision rev : revisiones) {
                revisionesDTO.add(toRevisionDTO(rev));
            }
            return new ResponseEntity<>(revisionesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/all/total")
    public ResponseEntity<Long> getTotalRevisiones(){
        try {
            long total = revisionService.getTotalRevisiones();
            return new ResponseEntity<>(total, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/review/new")
    @Transactional
    public ResponseEntity<RevisionDTO> addRevision(@Valid @RequestBody RevisionCreateDTO revisionCreateDTO){
        Revision revision = new Revision();
        setRevision(revisionCreateDTO, revision);
        revisionService.save(revision);
        return new ResponseEntity<>(toRevisionDTO(revision), HttpStatus.CREATED);
    }



    @PutMapping("/revisiones/{id}")
    public ResponseEntity<?> updateRevision(@PathVariable Integer id, @Valid @RequestBody RevisionCreateDTO revision){
        try {
            Revision currentRevision = revisionService.getRevisionById(id);
            setRevision(revision, currentRevision);
            revisionService.save(currentRevision);
            return new ResponseEntity<>(currentRevision, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/revisiones/{id}")
    public ResponseEntity<?> deleteRevision(@PathVariable Integer id){
        try {
            revisionService.deleteRevision(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Revision>(HttpStatus.NOT_FOUND);
        }
    }

    private RevisionDTO toRevisionDTO(Revision revision){
        RevisionDTO revisionDTO = new RevisionDTO();
        revisionDTO.setReview_id(revision.getIdRevision());
        revisionDTO.setPlate(revision.getVehiculo() != null ? revision.getVehiculo().getMatricula() : null);
        revisionDTO.setUser_id(revision.getUsuario() != null ? revision.getUsuario().getIdUsuario() : null);
        revisionDTO.setReview_date(revision.getFechaRevision());
        revisionDTO.setActual_km(revision.getKilometrajeActual());
        revisionDTO.setReview_note(revision.getDiagnosticoResultado());
        revisionDTO.setImporte(revision.getImporte());
        return revisionDTO;
    }

    private void setRevision(@RequestBody @Valid RevisionCreateDTO revisionCreateDTO, Revision revision) {
        revision.setFechaRevision(revisionCreateDTO.getReview_date());
        revision.setKilometrajeActual(revisionCreateDTO.getActual_km());
        revision.setDiagnosticoResultado(revisionCreateDTO.getReview_note());
        revision.setImporte(revisionCreateDTO.getImporte());
        Vehiculo vehiculo = vehiculoService.getVehiculoById(revisionCreateDTO.getPlate());
        if (revisionCreateDTO.getNext_review_date() != null) {
            vehiculo.setFechaProximoMantenimiento(revisionCreateDTO.getNext_review_date());
        }
        vehiculoService.save(vehiculo);
        revision.setVehiculo(vehiculo);
        revision.setUsuario(usuarioService.getUsuarioById(revisionCreateDTO.getUser_id()));
    }
}
