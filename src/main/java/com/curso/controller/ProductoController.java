package com.curso.controller;

import java.util.Collection;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.curso.domain.Producto;
import com.curso.excepciones.ProductosException;
import com.curso.service.ComprasService;
import com.curso.service.ProductoService;

@Controller
public class ProductoController {

	@Autowired
    private ProductoService productoService;
	@Autowired
    private ComprasService comprasService;
	
	//  /producto?id=P1234
	@RequestMapping("/producto")
	public String producto(@RequestParam("id") String productId, Model model) {
		
		Producto p = productoService.getProductoPorId(productId);
		
        model.addAttribute("producto", p);
        
		return "producto"; //WEB-INF/jsp/productos.jsp
	}
	
	//lista de categorias por categoria
	
	//  /productos/{categoria}
	//  /productos/Smart+Phone
	@RequestMapping("/productos/{categoria}")
	public String productosPorCategoria(@PathVariable("categoria") String categoriaProducto,Model model) {
		
		Collection<Producto> lista = productoService.getProductosPorCategoria(categoriaProducto);
		
		model.addAttribute("productos", lista);
		return "productos";
	}
	
	//  /productos/tablet/Apple
	@GetMapping("Productos/{categoria}/{fabricante}")
	public String getProductorPorCategoriaYColor(@PathVariable("categoria") String categoriaProducto, @PathVariable("fabricante") String fabricante, Model model) {
		//falta llamar a la capa de negocio y pasar model
		return "productos";
	}
    
	@RequestMapping("/productos")
    public String productos(Model model) {
		
		//Para probar producto-exception.jsp
//		String s =null;
//		s.toCharArray();

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
	
	// CREAR EL FORULARIO - GET
	
	@GetMapping(value = "/productos/nuevo") 
	public String getCrearNuevoProductoFormulario(Model model) { 
		Producto nuevoProducto = new Producto(); 
		nuevoProducto.setNombre("Nuevo");
		model.addAttribute("nuevoProducto", nuevoProducto); 
		return "crear-producto"; 
	} 
	
	// PROCESAR LOS DATOS DEL FORMULARIO - POST
	
	@PostMapping(value = "/productos/nuevo") 
    public String procesarCrearNuevoProductoFormulario(
           @ModelAttribute("nuevoProducto") @Valid Producto nuevoProducto, 
           BindingResult bindingResult) throws ProductosException { 
		//falta validar
		System.out.println(bindingResult.hasErrors());
		if(bindingResult.hasErrors()) {
			return "crear-producto";
		}
//		try {
			productoService.crearProducto(nuevoProducto);
			return "redirect:/productos";  
//		} catch (ProductosException e) {
//			model.addAttribute("nuevoProducto", nuevoProducto); 
//			model.addAttribute("error",e.getClaveMensaje());
//			return "crear-producto";
//		} 
		//model.addAttribute("productos", 
		//        productoService.getTodosProductos());
		//return "productos";
		//return "redirect:/productos";  
    }
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleException( Exception exception) {
		
         ModelAndView mav = new ModelAndView();
         
        if(exception instanceof ProductosException){
        	
            mav.addObject("arg0","Error Producto");
            mav.addObject("claveMensage",((ProductosException)exception).getClaveMensaje());
            
        }else{
        	
             mav.addObject("arg0",exception.getMessage());
             mav.addObject("claveMensage","error.inexperado");
             Logger.getAnonymousLogger().severe(exception.getMessage());
             
        }
        
        mav.setViewName("producto-exception");
        
        return mav;

    }
	
}
