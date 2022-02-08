<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet"
              href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
        <title>Productos</title>
    </head>
    <body>
        <section>
            <div class="jumbotron">
                <div class="container">
                    <h1>Productos</h1>
                    <p>Todos los productos disponibles en tienda</p>
                </div>
            </div>
        </section>

        <section class="container">
            <div class="row">
                <c:forEach items="${productos}" var="producto">
                    <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                        <div class="thumbnail">
                            <div class="caption">
                                <h3>${producto.nombre}</h3>
                                <p>${producto.descripcion}</p>
                                <p>${producto.precioUnitario} €</p>
                                <p>Hay  ${producto.unidadesEnStock} unidades in stock</p>
                                <p>
	                                <a class="btn btn-primary" 
	                                href="comprar?id=${producto.idProducto}" 
	                                role="button">Comprar 1 unidad</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="row">
            	<a class="btn btn-primary" 
	            href="productos/nuevo" 
	            role="button">NUEVO</a>
	            <a class="btn btn-primary" 
	            href="<spring:url value="/productos/nuevo"/>" 
	            role="button">NUEVO</a>
            </div>
        </section>
    </body>
</html>
