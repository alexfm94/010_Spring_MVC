package com.curso.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.curso.domain.Producto;

@Repository
@Qualifier("JPAProductoRepository")
@Transactional(propagation = Propagation.REQUIRED)
public class JPAProductoRepository implements ProductoRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Producto> getAllProductos() {
		Query q = em.createNamedQuery("Producto.findAll");
		return q.getResultList();
	}

	/**
	 * busca el producto por id si no existe devuelve null
	 */
	@Override
	public Producto getProductoPorId(String idProducto) {
		return em.find(Producto.class, idProducto);
	}

	@Override
	public List<Producto> getProductosPoCategoria(String categoria) {
		Query q = em.createNamedQuery("Producto.findByCategoria");
		q.setParameter("categoria", categoria);
		return q.getResultList();
	}

	@Override
	public void add(Producto producto) {
		em.persist(producto);
	}
	
}
