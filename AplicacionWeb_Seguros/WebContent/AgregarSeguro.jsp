<%@page import="entidad.TipoSeguros"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agregar Seguro</title>
</head>
<body>

	<a href="Inicio.jsp">Inicio</a>
	<a href="servletSeguros?Agregar=1">Agregar Seguro</a>
	<a href="servletSeguros?Listar=1">Listar Seguro</a>
	<br>
	
	<h1>Agregar Seguro</h1>
	<form action="servletSeguros" method="post">
	<p>Id Seguro: <%= request.getAttribute("idSeguroNuevo") %></p>
	
	<p>Descripcion: <input type="text" name="txtDescripcion"></p>
	
	<p>Tipo de Seguro: <select name="TipoSeguro">
		<%
			ArrayList<TipoSeguros> listadoTipoSeguro = null;
			out.print("<option value='0'>Seleccione Tipo de Seguro</option>");
			if((ArrayList<TipoSeguros>) request.getAttribute("listaTipoSeguro")!=null){
				listadoTipoSeguro = (ArrayList<TipoSeguros>) request.getAttribute("listaTipoSeguro");
				for(TipoSeguros tipoSeguro : listadoTipoSeguro){
					out.print("<option value='"+tipoSeguro.getId()+"'>"+tipoSeguro.getDescripcion()+"</option>");
				}
			}
		%>
	</select></p>
	
	<p>Costo de Contratacion: <input type="text" name="txtCostContratacion"></p>
	
	<p>Costo Maximo Asegurado: <input type="text" name="txtCostoAsegurado"></p>
	
	<input type="submit" value="Aceptar" name="btnAceptar">
	
	 </form> 
	 
	 <% String mensaje = "";
		if(request.getAttribute("mensajeEstado")!= null){
			mensaje = (String)request.getAttribute("mensajeEstado");
		}
	%>
		<%if(mensaje!=""){
		%>
		<%= mensaje %>
		<%}
	%>
</body>
</html>