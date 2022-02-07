package com.curso.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.domain.Producto;
import com.curso.service.ComprasService;
import com.curso.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
    private ProductoService productoService;
	@Autowired
    private ComprasService comprasService;
	
	@RequestMapping("/producto")
	public String producto(Model model) {
		Producto iphone = new Producto("P1234","iPhone 5s",
                new  BigDecimal(500));
        iphone.setDescripcion("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
        iphone.setCategoria("Smart Phone");
        iphone.setFabricante("Apple");
        iphone.setUnidadesEnPedido(1000);
        
        model.addAttribute("producto", iphone);
        
		return "producto"; //WEB-INF/jsp/productos.jsp
	}
    
	@RequestMapping("/productos")
    public String productos(Model model) {

		model.addAttribute("productos", 
				productoService.getTodosProductos());

		return "productos";
    }
	
	@RequestMapping("/comprar")
    public String comprar(@RequestParam("id") String productId, Model model) {

		// leer parametro id
		System.out.println("id es.... " + productId);
		comprasService.comprar(productId, 1);
		model.addAttribute("productos", 
				productoService.getTodosProductos());
		
		return "productos";
    }
	
	public ProductoController() {
		System.out.println("... iniciando ProductoController");
	}
	
}
