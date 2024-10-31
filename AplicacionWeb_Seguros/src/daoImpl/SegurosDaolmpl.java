package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.SegurosDao;
import entidad.Seguros;
import entidad.TipoSeguros;

public class SegurosDaolmpl implements SegurosDao{
	
	private static final String insert = "INSERT INTO seguros(descripcion, idTipo, costoContratacion, costoAsegurado) VALUES(?, ?, ?, ?)";
	private static final String readall = "SELECT seg.idSeguro,seg.descripcion,tiposeg.idTipo,tiposeg.descripcion AS descripcionTipoSeguro,seg.costoContratacion,seg.costoAsegurado FROM seguros seg INNER JOIN tiposeguros tiposeg ON seg.idTipo = tiposeg.idTipo";
	
	@Override
	public boolean insert(Seguros seguro) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			
			statement = conexion.prepareStatement(insert);
			statement.setString(1, seguro.getDescrpcion());
			statement.setInt(2, seguro.getIdTipo().getId());
			statement.setDouble(3, seguro.getCostoContratacion());
			statement.setDouble(4, seguro.getCostoAsegurado());
			if(statement.executeUpdate()>0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			}catch(SQLException e){
				e.printStackTrace();
				try {
					conexion.rollback();
				}catch(SQLException e0) {
					e0.printStackTrace();
				}
			}
		return isInsertExitoso;
	}
	
	
	@Override
	public ArrayList<Seguros> obtenerSeguros() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Seguros> lista = new ArrayList<Seguros>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Seguros seguro = new Seguros();
				seguro.setID(resultSet.getInt("idSeguro"));
				seguro.setDescrpcion(resultSet.getString("descripcion"));
				seguro.setCostoContratacion(resultSet.getDouble("costoContratacion"));
				seguro.setCostoAsegurado(resultSet.getDouble("costoAsegurado"));
					TipoSeguros TSeguro = new TipoSeguros();
					TSeguro.setId(resultSet.getInt("idTipo"));
					TSeguro.setDescripcion(resultSet.getString("descripcionTipoSeguro"));
				seguro.setIdTipo(TSeguro);
				lista.add(seguro);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		conexion.cerrarConexion();
		return lista;
	}
	@Override
	public ArrayList<Seguros> filtrarSegurosId(int id) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String filtro = "";
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Seguros> lista = new ArrayList<Seguros>();
		Conexion conexion = Conexion.getConexion();
		
		if(id!=0) {
			filtro = "SELECT seg.idSeguro,seg.descripcion,tiposeg.idTipo,tiposeg.descripcion AS descripcionTipoSeguro,seg.costoContratacion,seg.costoAsegurado FROM seguros seg INNER JOIN tiposeguros tiposeg ON seg.idTipo = tiposeg.idTipo WHERE seg.idTipo="+id;
		}else {
			filtro = "SELECT seg.idSeguro,seg.descripcion,tiposeg.idTipo,tiposeg.descripcion AS descripcionTipoSeguro,seg.costoContratacion,seg.costoAsegurado FROM seguros seg INNER JOIN tiposeguros tiposeg ON seg.idTipo = tiposeg.idTipo";
		}
		
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(filtro);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Seguros seguro = new Seguros();
				seguro.setID(resultSet.getInt("idSeguro"));
				seguro.setDescrpcion(resultSet.getString("descripcion"));
				seguro.setCostoContratacion(resultSet.getDouble("costoContratacion"));
				seguro.setCostoAsegurado(resultSet.getDouble("costoAsegurado"));
					TipoSeguros TSeguro = new TipoSeguros();
					TSeguro.setId(resultSet.getInt("idTipo"));
					TSeguro.setDescripcion(resultSet.getString("descripcionTipoSeguro"));
				seguro.setIdTipo(TSeguro);
				lista.add(seguro);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		return lista;
		
	}
	@Override
	public int obtenerIDMaximo() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int UltimoID = 0;
		PreparedStatement statement;
		ResultSet resulSet;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement("SELECT MAX(idSeguro) FROM seguros");
			resulSet = statement.executeQuery();
			
				while(resulSet.next()){
					UltimoID = resulSet.getInt(1);
				}		
				
			}catch(SQLException e){
				e.printStackTrace();
		}	
	return UltimoID;
	}
}
