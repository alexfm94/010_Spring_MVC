<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  

<html> 
    <title>Producto no encontrado</title> 
    <link rel="stylesheet"
              href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

</head> 
<body> 
    <section> 
        <div class="jumbotron"> 
            <div class="container"> 
                <h1 class="alert alert-danger"> 
                    
                    <spring:message code="${claveMensage}" />
                    
                </h1> 
                Mensaje ${arg0}
            </div> 
        </div> 
    </section> 

    <section> 
        <div class="container"> 
            <p> 
                <a href="<spring:url value="/productos" />" 
                   class="btn btn-primary"> 
                    <span class="glyphicon-hand-left glyphicon">
                    </span> productos 
                </a> 
            </p> 
        </div> 

    </section> 
</body> 
</html>