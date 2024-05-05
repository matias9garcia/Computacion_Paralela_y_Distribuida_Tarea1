package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfazDeServer extends Remote {
	ArrayList<Persona> getPersonas() throws RemoteException;
	Object[] getUF() throws RemoteException;
	String getDataFromApi() throws RemoteException;
}
