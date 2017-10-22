package client;

import java.sql.Timestamp;
import java.util.Date;

public class Object {

	private int numSecuencia;
	
	private int total;
	
	private long tiempoArchivo;
	
	private Timestamp fechaCreacion;
	
	public Object(int numSecuencia, int total, long tiempoArchivo, Timestamp fechaCreacion) {
		super();
		this.numSecuencia = numSecuencia;
		this.total = total;
		this.tiempoArchivo = tiempoArchivo;
		this.fechaCreacion = fechaCreacion;
	}

	public int getNumSecuencia() {
		return numSecuencia;
	}

	public void setNumSecuencia(int numSecuencia) {
		this.numSecuencia = numSecuencia;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public int getTotal(){
		return total;
	}
	
	public void setTotal(int total){
		this.total = total;
	}
	
	public long getTiempoArchivo(){
		return tiempoArchivo;
	}
	
	public void setTiempoArchivo(long tiempoArchivo){
		this.tiempoArchivo = tiempoArchivo;
	}
	
}