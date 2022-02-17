
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.domain.repository.ProductoRepository;
import com.curso.excepciones.ProductosException;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
	
    @Autowired
    @Qualifier("JPAProductoRepository")
    private ProductoRepository productoRepositorio;

    public ProductoServiceImpl() {
        System.out.println("........... intanciando ProductoServiceImpl");
    }

     
     
    @Override
    public List<Producto> getTodosProductos() {
       return productoRepositorio.getAllProductos();
    }
    
    @Override
    public List<Producto> getProductosPorCategoria(String categoria) {
      return productoRepositorio.getProductosPoCategoria(categoria);
    }

    @Override
    public Producto getProductoPorId(String idProducto) {
      Producto producto =productoRepositorio.getProductoPorId(idProducto);
      return producto;
    }
    
    @Override
    public void crearProducto(Producto producto) throws ProductosException {
    	if(productoRepositorio.getProductoPorId(producto.getIdProducto()) != null) {
    		//hay producto con ese id
    		throw new ProductosException("No se puede crear. Ya hay un producto con ese código",producto.getIdProducto(),"producto.alta.error.yaexisteCodigo");
    	}
       productoRepositorio.add(producto);
    }
    
}
