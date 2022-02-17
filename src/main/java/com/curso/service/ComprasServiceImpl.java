package com.curso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ComprasServiceImpl implements ComprasService {

	@Autowired
	@Qualifier("JPAProductoRepository")
	private ProductoRepository repositorio;
	
	@Override
	public void comprar(String idProducto, int cantidad) {
		
//		// primero valido datos
//		if( ! repositorio.existeProducto()){
//			throw new RuntimeException("No existe el producto");
//		} 
		// si existe decremento stock
		Producto p = repositorio.getProductoPorId(idProducto);
		p.setUnidadesEnStock(p.getUnidadesEnStock() - cantidad);
		
	}

}
