package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfazDeServer extends Remote {
	ArrayList<Persona> getPersonas() throws RemoteException;
	Object[] getUF() throws RemoteException;
	String getDataFromApi() throws RemoteException;
	
	Persona newPersona(String nombre, String apellido, int creditos, String tipo_contrato) throws RemoteException;
	
    
    // TODO MUTEX
	boolean requestMutex() throws RemoteException;
	void releaseMutex() throws RemoteException;
	
}
