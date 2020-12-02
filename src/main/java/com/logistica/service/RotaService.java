//package com.logistica.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.logistica.model.Carga;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.logistica.model.Rota;
//import com.logistica.repository.RotaRepository;
//
//@Service
//public class RotaService {
//
//	@Autowired
//	private RotaRepository rotaRepository;
//
//	List<Rota> bases = new ArrayList<Rota>();
//
//	public List<Rota> list() {
//		return rotaRepository.findAll();
//	}
//
//	public Rota findByIdOrCreate(Rota rota) {
//		boolean existId = rota.getId() != null ? true : false;
//		if (existId) {
//			Optional<Rota> opRota = rotaRepository.findById(rota.getId());
//			if (opRota.isPresent()) {
//				return opRota.get();
//			}
//		} else {
//			return rotaRepository.save(rota);
//		}
//		return null;
//	}
//}
