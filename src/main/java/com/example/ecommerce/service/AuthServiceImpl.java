package com.example.ecommerce.service;

import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.LoginResponse;
import com.example.ecommerce.dto.RegisterRequest;
import com.example.ecommerce.dto.RegisterResponse;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import io.jsonwebtoken.security.Keys;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RoleRepository roleRepository;

    @Value("${ecommerce.jwt.secret}")
    private String secretKey;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        Optional<User> optionalUser=userRepository.finbyEmail(registerRequest.email());
        if(optionalUser.isPresent()){
            throw new ApiException("Girmiş olduğunuz email ile daha önce kayıt olmuşsunuz.", HttpStatus.CONFLICT);
        }
        String encodedPassword=passwordEncoder.encode(registerRequest.password());
        Role role = roleRepository.finbyRole("USER");
        User user = new User();
        user.setEmail(registerRequest.email());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(role));
        user=userRepository.save(user);
        return new RegisterResponse(user.getEmail(),"Kullanıcı Başarı ile kaydoldu");
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> optionalUser=userRepository.finbyEmail(loginRequest.email());
        if(!optionalUser.isPresent()){
            throw new ApiException("Kullanıcı mevcut değil",HttpStatus.NOT_FOUND);
        }
        else if(!passwordEncoder.matches(loginRequest.password(),optionalUser.get().getPassword())){

            throw new ApiException("Şifre veya Email adresinizi yanlış girdiniz.",HttpStatus.UNAUTHORIZED);
        }

        return  new LoginResponse(optionalUser.get().getEmail(),"Kullanıcı başarı ile giriş yaptı","token");
    }





}
