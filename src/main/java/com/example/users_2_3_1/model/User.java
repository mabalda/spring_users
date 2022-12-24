package com.example.users_2_3_1.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 1, max = 50)
    @NotEmpty
    private String name;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @Column(name = "email")
    @Size(min = 1, max = 100)
    @NotEmpty
    private String email;

    @Column(name = "age")
    @NotEmpty
    @Min(value = 14)
    private Integer age;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "requestForAdmin")
    private boolean requestForAdmin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        Set<Role> roles = getRoles();

        for (Role role : roles) {
            if (role.getRole().equals("ROLE_ADMIN")) {
                return true;
            }
        }

        return false;
    }
}
