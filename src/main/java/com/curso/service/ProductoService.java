
package com.curso.service;

import com.curso.domain.Producto;
import com.curso.excepciones.ProductosException;

import java.util.List;


public interface ProductoService {
    
    List <Producto> getTodosProductos();
    List<Producto> getProductosPorCategoria(String categoria); 
    // List<Producto> getProductosPorFiltro(Map<String, List<String>> paramsFiltro);
    Producto getProductoPorId(String idProducto);
    void crearProducto(Producto producto) throws ProductosException;
}
