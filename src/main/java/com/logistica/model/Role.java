//package com.logistica.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.*;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity
//@Getter
//@Setter
//public class Role implements GrantedAuthority {
//
//	@Id
//	private String nomeRole;
//	@ManyToMany(fetch = FetchType.EAGER)
//	private List<Usuario> usuarios = new ArrayList<>();
//
//	@Override
//	public String getAuthority() {
//		return this.nomeRole;
//	}
//
//}
