package com.banco.service;

import com.banco.dto.LoginRequest;
import com.banco.dto.LoginResponse;
import com.banco.model.User;
import com.banco.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
// The methods in this class must executed into a transaction
//Una transacción es una unidad de trabajo que debe completarse en su totalidad para que los cambios sean permanentes en la base de datos. Si algo falla durante la ejecución de la transacción, todos los cambios deben deshacerse (rollback) para mantener la consistencia.

public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUserDni(request.getUserDni())
                .orElseThrow(() -> new RuntimeException("User not found"));


        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect Password");
        }

        String token = jwtService.generateToken(user);
        return new LoginResponse();
    }
}
