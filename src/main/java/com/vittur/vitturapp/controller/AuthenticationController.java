package com.vittur.vitturapp.controller;

import com.vittur.vitturapp.dtos.JwtResponseDTO;
import com.vittur.vitturapp.dtos.LoginDTO;
import com.vittur.vitturapp.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        JwtResponseDTO response = authenticationService.authenticate(loginDTO.getUsername(), loginDTO.getPassword());
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
    }
}
