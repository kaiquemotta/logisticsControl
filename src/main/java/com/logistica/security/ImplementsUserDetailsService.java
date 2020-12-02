//package com.logistica.security;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Repository;
//
//import com.logistica.model.Usuario;
//import com.logistica.repository.UsuarioRepository;
//
//@Repository
//@Transactional
//public class ImplementsUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UsuarioRepository usuarioRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//		Usuario usuario = usuarioRepository.findByLogin(login);
//		if (usuario == null) {
//			throw new UsernameNotFoundException("Usuario nao encontrado");
//		}
//		return new User(usuario.getUsername(), usuario.getPassword(), true,true,true,true,usuario.getAuthorities());
//	}
//
//}
