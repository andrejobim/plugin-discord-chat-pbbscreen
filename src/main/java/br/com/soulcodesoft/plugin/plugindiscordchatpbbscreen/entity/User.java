package br.com.soulcodesoft.plugin.plugindiscordchatpbbscreen.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "public")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(allocationSize=1, name="user_seq", sequenceName="user_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> role); // Retorna as autoridades do usuário
    }

    @Override
    public String getPassword() {
        return password; // Retorna a senha
    }

    @Override
    public String getUsername() {
        return username; // Retorna o nome de usuário
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Ajuste conforme necessário
    }

    @Override
    public boolean isEnabled() {
        return true; // Ajuste conforme necessário
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
