<%@page import="entidad.Seguros"%>
<%@page import="entidad.TipoSeguros"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Listar seguros</title>
</head>
<body>
	<div style="margin:0px; padding:0px">
		<a href="Inicio.jsp" style="margin:10px">Inicio</a> 
		<a href="servletSeguros?Agregar=1" style="margin:10px">Agregar seguro</a> 
		<a href="servletSeguros?Listar=1" style="margin:10px">Listar seguro</a> 
	</div>
	<form action="servletSeguros" method="post" style="margin:0px; padding:0px">
		<h1 style="margin:0px; padding:0px">Tipos de seguros de la base de datos</h1>
		<div>
			Búsqueda por tipo de seguros:
			<select name="tipoSeguro">
				<%
					ArrayList<TipoSeguros> listadoTipoSeguro2 = null;
					out.print("<option value='0'>Seleccione Tipo de Seguro</option>");
					if(request.getAttribute("listaTipoSeguro") != null) {
						listadoTipoSeguro2 = (ArrayList<TipoSeguros>) request.getAttribute("listaTipoSeguro");
						for (TipoSeguros tipoSeguro : listadoTipoSeguro2) {
							out.print("<option value='" + tipoSeguro.getId() + "'>" + tipoSeguro.getDescripcion() + "</option>");
						}
					}
				%>
			</select>
			<input type="submit" value="Filtrar" name="btnFiltrar">
		</div>
	</form>

	<% 
		ArrayList<TipoSeguros> listaTipoSeguros = null;
		if(request.getAttribute("listaTipoSeguros")!=null)
		{
			listaTipoSeguros = (ArrayList<TipoSeguros>) request.getAttribute("listaTipoSeguros");
		}
	
	 %>
	 
	 <%
	 ArrayList<Seguros> listaSeguro = null;
	 if(request.getAttribute("listados")!=null){
	 listaSeguro = (ArrayList<Seguros>)request.getAttribute("listados");
	 }
	  %>


<table id="table_id" class="display" border="1" style="margin:0px; padding:0px">
    <thead>
        <tr>
            <th style="text-align: center;height: 10vh">ID</th>
            <th style="text-align: center;height: 10vh">Descripción seguro</th>
            <th style="text-align: center;height: 10vh">Descripción Tipo de Seguro</th>
            <th style="text-align: center;height: 10vh">Costo de Contratación</th>
            <th style="text-align: center;height: 10vh">Costo Máximo Asegurado</th>
        </tr>
    </thead>
    <tbody>
       <%  if(listaSeguro!=null)
		for(Seguros seguro : listaSeguro) 
		{
	%>
		<tr>  
		    <td style="text-align: center;height: 10vh"><%=seguro.getID() %></td>
            <td style="text-align: center;height: 10vh"><%=seguro.getDescrpcion() %></td>
            <td style="text-align: center;height: 10vh"><%=seguro.getIdTipo().getDescripcion() %></td>
            <td style="text-align: center;height: 10vh"><%=seguro.getCostoContratacion() %></td>
            <td style="text-align: center;height: 10vh"><%=seguro.getCostoAsegurado() %></td>
		</tr>
	<%  } %>
    </tbody>
</table>

</body>
</html>