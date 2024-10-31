package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.TipoSegurosDao;
import entidad.TipoSeguros;

public class TipoSegurosDaoImpl implements TipoSegurosDao {

	@Override
	public ArrayList<TipoSeguros> obtenerTipoSeguros() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<TipoSeguros> lista = new ArrayList<TipoSeguros>();
		Connection conn = null;
		Statement st = null;
		
		try{
			conn = (Connection) Conexion.getConexion().getSQLConexion();
			st = (Statement) conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tiposeguros");
			
			while(rs.next()){
				TipoSeguros seguro = new TipoSeguros();
				seguro.setId(rs.getInt("idTipo"));
				seguro.setDescripcion(rs.getString("descripcion"));
				lista.add(seguro);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn != null) {
				try {
					Conexion.getConexion().cerrarConexion();
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(st != null) {
				try {
					st.close();
				} 
				catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		}
		
		return lista;
    }

}
