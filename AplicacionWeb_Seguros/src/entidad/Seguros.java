package entidad;

public class Seguros {
	
	private int ID;
	private String Descrpcion;
	private TipoSeguros IdTipo;
	private double CostoContratacion;
	private double CostoAsegurado;
	
	public Seguros() {
		
	}
	
	public Seguros(int id, String Descr, TipoSeguros tipo, double contratacion, double asegurado) {
		this.ID = id;
		this.Descrpcion = Descr;
		this.IdTipo = tipo;
		this.CostoContratacion = contratacion;
		this.CostoAsegurado = asegurado;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDescrpcion() {
		return Descrpcion;
	}

	public void setDescrpcion(String descrpcion) {
		Descrpcion = descrpcion;
	}

	public TipoSeguros getIdTipo() {
		return IdTipo;
	}

	public void setIdTipo(TipoSeguros idTipo) {
		IdTipo = idTipo;
	}

	public double getCostoContratacion() {
		return CostoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		CostoContratacion = costoContratacion;
	}

	public double getCostoAsegurado() {
		return CostoAsegurado;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		CostoAsegurado = costoAsegurado;
	}

}
