package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import common.InterfazDeServer;
import common.Persona;

public class Cliente {
	private InterfazDeServer server;
	
	public Cliente() {	}
	public void startCliente() throws RemoteException, NotBoundException {
		Registry registry = LocateRegistry.getRegistry("localhost", 5000);
		server = (InterfazDeServer) registry.lookup("server");
	}
	
	public ArrayList<Persona> getPersonas() throws RemoteException{
		ArrayList<Persona> listado_empleados = server.getPersonas();
		
		System.out.println("ID NOMBRE APELLIDO CREDITOS TIPO_JORNADA");
		
		for (int i = 0; i < listado_empleados.size(); i++) {
			Persona empleado = listado_empleados.get(i);
			String nombre, apellido, tipo_jornada;
			int id_empleado, creditos;
			
			id_empleado = empleado.getId_empleado();
			nombre = empleado.getNombre();
			apellido = empleado.getApellido();
			creditos = empleado.getCreditos();
			tipo_jornada = empleado.getTipo_jornada();
			
			System.out.println(id_empleado + " " + nombre + " "+ apellido + " " + creditos + " " + tipo_jornada);
		}
		
		return null;
	}
	
	String getDataFromApi() throws RemoteException {
		return server.getDataFromApi();
	}
	
	Object[] getUF() throws RemoteException {
		Object[] valores_de_uf = server.getUF();
		
		if(valores_de_uf == null) {
			System.out.println("Hubo un error, no llego nada de la API");
			return null;
		} else {
			String codigo = (String) valores_de_uf[0];
			String nombre = (String) valores_de_uf[1];
			String fecha = (String) valores_de_uf[2];
			String unidad_medida = (String) valores_de_uf[3];
			double valor = (double) valores_de_uf[4];
			
			System.out.println("Los valores obtenidos para la UF son:");
			System.out.println(codigo + " " + nombre + " " + fecha + " " + unidad_medida + " " + valor);
		}
		
		return valores_de_uf;
	}
	
	
}
