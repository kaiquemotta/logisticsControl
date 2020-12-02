//package com.logistica.model;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.persistence.*;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Usuario implements UserDetails {
//    @Id
//    private String login;
//    private String nomeCompleto;
//    private String senha;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(
//            name = "usuario_id", referencedColumnName = "login"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "nomeRole"))
//    private List<Role> roles = new ArrayList<>();
//
//
//    public Usuario() {
//    }
//
//    public Usuario(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.login = username;
//        this.senha = password;
//        this.roles = (List<Role>) authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles;
//    }
//
//    @Override
//    public String getPassword() {
//        // TODO Auto-generated method stub
//        return senha;
//    }
//
//    @Override
//    public String getUsername() {
//        // TODO Auto-generated method stub
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        // TODO Auto-generated method stub
//        return true;
//    }
//
//}
