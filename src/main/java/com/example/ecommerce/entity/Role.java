package com.example.ecommerce.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "role",schema = "ecommerce")
public class Role implements GrantedAuthority {
    public Role(String authority, String name) {
        this.authority = authority;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    @NotEmpty
    private String authority;
    @NotNull
    @NotBlank
    @NotEmpty
    private String name;
    @Override
    public String getAuthority() {
        return authority;
    }
}
