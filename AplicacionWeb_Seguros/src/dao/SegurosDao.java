package dao;

import java.util.ArrayList;
import entidad.Seguros;

public interface SegurosDao {
	
	public boolean insert(Seguros seguro);
	public ArrayList<Seguros> obtenerSeguros();
	public ArrayList<Seguros> filtrarSegurosId(int id);
	public int obtenerIDMaximo();

}
