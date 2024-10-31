package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SegurosDao;
import dao.TipoSegurosDao;
import daoImpl.SegurosDaolmpl;
import daoImpl.TipoSegurosDaoImpl;
import entidad.Seguros;
import entidad.TipoSeguros;

/**
 * Servlet implementation class servletSeguros
 */
@WebServlet("/servletSeguros")
public class servletSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idSeguroNuevo = 0;
		
		SegurosDao seguroDao = new SegurosDaolmpl();
		TipoSegurosDao tipoSeguroDao = new TipoSegurosDaoImpl();
		if(request.getParameter("Agregar") != null) {
			
			ArrayList<TipoSeguros> listaTipoSeguro = tipoSeguroDao.obtenerTipoSeguros();
			idSeguroNuevo = seguroDao.obtenerIDMaximo();
			idSeguroNuevo+=1;
			request.setAttribute("idSeguroNuevo", idSeguroNuevo);
			request.setAttribute("listaTipoSeguro", listaTipoSeguro);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");   
		    rd.forward(request, response);  
	
		}
		
		if(request.getParameter("Listar") != null) {
			SegurosDao seg = new SegurosDaolmpl();
			// REQUEST
			ArrayList<TipoSeguros> listaTipoSeguro = tipoSeguroDao.obtenerTipoSeguros();
			ArrayList<Seguros> listaSeguro = seg.obtenerSeguros();
			
			
			
				request.setAttribute("listaTipoSeguro", listaTipoSeguro);
				request.setAttribute("listados", listaSeguro);
			
			// DISPATCHER
			RequestDispatcher rd = request.getRequestDispatcher("ListarSeguros.jsp");   
		    rd.forward(request, response); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SegurosDao dao  =  new SegurosDaolmpl();
		TipoSegurosDao tipoSeguroDao = new TipoSegurosDaoImpl();
		int idSeguroNuevo = 0;
		boolean agregado = false;
		
		if(request.getParameter("btnAceptar") != null) {
			
			ArrayList<TipoSeguros> listaTipoSeguro = tipoSeguroDao.obtenerTipoSeguros();
			idSeguroNuevo = dao.obtenerIDMaximo();
			idSeguroNuevo+=1;
			
			request.setAttribute("idSeguroNuevo", idSeguroNuevo);
			request.setAttribute("listaTipoSeguro", listaTipoSeguro);
			
			String descripcion = "";
			Integer tipoSeguro = 0;
			Double costoContratacion = 0.0;
			Double costoAsegurado = 0.0;
			Boolean agregar = true;
			String mensajeEstado = "";
			
			try {
				
				descripcion = request.getParameter("txtDescripcion");
				tipoSeguro = Integer.parseInt(request.getParameter("TipoSeguro"));
				costoContratacion = Double.parseDouble(request.getParameter("txtCostContratacion"));
				costoAsegurado = Double.parseDouble(request.getParameter("txtCostoAsegurado"));
				
			}
			catch (Exception e){
				agregar = false;
				mensajeEstado = "Completar correctamente todos los campos";
        		
			}
			
			if(agregar) {
				
				if(tipoSeguro!=0) {
			
					Seguros seguro = new Seguros();
				
					seguro.setIdTipo(new TipoSeguros(tipoSeguro));
					seguro.setDescrpcion(descripcion);
					seguro.setCostoAsegurado(costoAsegurado);
					seguro.setCostoContratacion(costoContratacion);
					
					agregado = dao.insert(seguro);
					
					if(agregado) {
						
						mensajeEstado = "Se agregó el seguro correctamente";
						idSeguroNuevo = dao.obtenerIDMaximo();
						idSeguroNuevo+=1;
					}
					else {
						
						mensajeEstado = "No se pudo agregar el seguro";
						
					}
				}
				else {
					
					mensajeEstado = "Se debe de seleccionar un Tipo de seguro";
					
				}
			
			}
			//Reseteo todo para actualizar luego del nuevo añadido.
			request.setAttribute("idSeguroNuevo", idSeguroNuevo);
			request.setAttribute("mensajeEstado", mensajeEstado);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarSeguro.jsp");
			rd.forward(request, response);
		}
		if(request.getParameter("btnFiltrar")!=null) {
			ArrayList<TipoSeguros> listaTipoSeguro = tipoSeguroDao.obtenerTipoSeguros();
			SegurosDao seg = new SegurosDaolmpl();
			int id=0;
			
			id = Integer.parseInt(request.getParameter("tipoSeguro"));
			ArrayList<Seguros> listaFiltrada = seg.filtrarSegurosId(id);
			
			request.setAttribute("listados", listaFiltrada);
			request.setAttribute("listaTipoSeguro", listaTipoSeguro);
			RequestDispatcher rd = request.getRequestDispatcher("/ListarSeguros.jsp");   
		    rd.forward(request, response); 
		}
	}
}
