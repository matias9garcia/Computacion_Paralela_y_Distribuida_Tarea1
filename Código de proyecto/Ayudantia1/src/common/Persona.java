package common;

import java.io.Serializable;

public class Persona implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id_empleado;
	private String nombre;
	private String apellido;
	private int creditos;
	private String tipo_jornada;
	
	public Persona(int id_empleado, String nombre, String apellido, int creditos, String tipo_jornada) {
		this.setId_empleado(id_empleado);
		this.setNombre(nombre);
		this.setApellido(apellido);		
		this.setCreditos(creditos);	
		this.setTipo_jornada(tipo_jornada);	
	}
	
	public int getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getCreditos() {
		return creditos;
	}
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	public String getTipo_jornada() {
		return tipo_jornada;
	}
	public void setTipo_jornada(String tipo_jornada) {
		this.tipo_jornada = tipo_jornada;
	}
	
}
