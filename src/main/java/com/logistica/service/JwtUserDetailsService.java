//package com.logistica.service;
//
//import com.logistica.model.Usuario;
//import com.logistica.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Service
//public class JwtUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Usuario> opUsuario = Optional.ofNullable(usuarioRepository.findByLogin(username));
//        if (opUsuario.isPresent()) {
//            Usuario us = opUsuario.get();
//            return new Usuario(us.getUsername(), us.getPassword(), us.getAuthorities());
//        } else {
//            if ("springuser".equals(username)) {
//                return new Usuario("springuser", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//            } else {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//            }
//        }
//    }
//}
