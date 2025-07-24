package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exceptions.ApiException;
import com.example.ecommerce.mapper.StoreMapper;
import com.example.ecommerce.repository.UserRepository;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

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
    private final RoleService roleService;
    @Autowired
    private final StoreMapper storeMapper;

    @Value("${ecommerce.jwt.secret}")
    private String secretKey;

    @Override
    public RegisterResponse register(RegisterRequest authRequestDto) {

        Optional<User> optionalUser=userRepository.finbyEmail(authRequestDto.email());
        if(optionalUser.isPresent()){
            throw new ApiException("Girmiş olduğunuz email ile daha önce kayıt olmuşsunuz.", HttpStatus.CONFLICT);
        }
        String encodedPassword=passwordEncoder.encode(authRequestDto.password());
        Optional<Role> role = roleService.getRoleById(authRequestDto.roleId());
        Role finalRole;
        if (role.isPresent()) {
            finalRole = role.get();
        } else {
            roleService.addAllDefaultRole();
            finalRole =roleService.getRoleById(authRequestDto.roleId()).orElseThrow(()->new ApiException("Aranan Rol bulunamadı",HttpStatus.NOT_FOUND));

        }

        User user = new User();
        user.setName(authRequestDto.name());
        if(authRequestDto.roleId()==2){
            user.setStore(storeMapper.toEntity(authRequestDto.store()));
        }
        user.setEmail(authRequestDto.email());
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(finalRole));

        String token = generateToken(user.getEmail());
        user.setToken(token);
        user=userRepository.save(user);
        return new RegisterResponse(user.getEmail(),"Kullanıcı Başarı ile kaydoldu",token);
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
        String token = generateToken(optionalUser.get().getEmail());
        optionalUser.get().setToken(token);
        userRepository.save(optionalUser.get());
        return  new LoginResponse(optionalUser.get().getEmail(),"Kullanıcı başarı ile giriş yaptı",optionalUser.get().getPassword());
    }


    private Key getSigningKey() {
        byte[] keyBytes = io.jsonwebtoken.io.Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(String email) {
        long expiration = 3600000; // 1 saat (istersen dışarıdan al)
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }



}
