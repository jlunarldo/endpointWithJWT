package apiAuthentication.example.ApiAuth.Service;

import apiAuthentication.example.ApiAuth.Entities.Rol;
import apiAuthentication.example.ApiAuth.Entities.UserGeneral;
import apiAuthentication.example.ApiAuth.Model.Request.LoginRequest;
import apiAuthentication.example.ApiAuth.Model.Request.RegisterRequest;
import apiAuthentication.example.ApiAuth.Model.Response.AuthResponse;
import apiAuthentication.example.ApiAuth.Repository.RolRepository;
import apiAuthentication.example.ApiAuth.Repository.UserGeneralRepository;
import apiAuthentication.example.ApiAuth.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Autowired
    private final UserGeneralRepository userGeneralRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Autowired
    private RolRepository rolRepository;
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user= userGeneralRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {
        Rol rol = rolRepository.findByRoleEnum(request.getRoleEnum())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + request.getRoleEnum()));


        UserGeneral user = UserGeneral.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .roles(Set.of(rol))
                .build();

        userGeneralRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}