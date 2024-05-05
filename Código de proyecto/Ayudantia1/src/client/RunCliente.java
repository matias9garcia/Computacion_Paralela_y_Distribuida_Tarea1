package client;

import java.io.IOException;
import java.rmi.NotBoundException;

public class RunCliente {
	public static void main(String[] args) throws NotBoundException, IOException {
		Cliente cliente = new Cliente();
		cliente.startCliente();
		System.out.println("Cliente arriba!!");
		
		System.out.println("Cliente arriba!! ");
		System.out.println("### Bienvenido a Acme Corp ### ");
		System.out.println("¿Qué quiere hacer hoy? ");
		System.out.println("Para ver la Base de Datos de empleados, aprete 1. ");
		System.out.println("Para obtener los valores de la UF, aprete 2. ");
		System.out.println("Para obtener datos de la API, aprete 3. ");
		
		while(true) {	
			System.out.println("Ingrese la opción: ");
			char bufferInput = (char) System.in.read();
			
			if (bufferInput == '1') {
				cliente.getPersonas();
				System.out.println("###");
			} else if (bufferInput == '2') {
				cliente.getUF();
				System.out.println("###");
			} else if (bufferInput == '3') {
				System.out.println(cliente.getDataFromApi());
				System.out.println("###");
			}
			// Limpiar el buffer
            System.in.skip(System.in.available());
            
		}
	}
}
