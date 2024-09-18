package com.cine_reserva_backend.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cine_reserva_backend.model.dto.LoginDTO;
import com.cine_reserva_backend.model.dto.RegisterDTO;
import com.cine_reserva_backend.service.UsuarioService;
import com.cine_reserva_backend.web.config.JwtUtil;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO) {
        try {
            usuarioService.registrarUsuario(registerDTO);
            return ResponseEntity.ok("Usuario Registrado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        final UserDetails userDetails = usuarioService.loadUserByUsername(loginDTO.getEmail());
        return ResponseEntity.ok(jwtUtil.create(userDetails.getUsername()));
    }

}
