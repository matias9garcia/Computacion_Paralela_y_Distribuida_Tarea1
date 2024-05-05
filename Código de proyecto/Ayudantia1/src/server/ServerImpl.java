package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.InterfazDeServer;
import common.Persona;

public class ServerImpl implements InterfazDeServer{

	public ServerImpl() throws RemoteException {
		conectarBD();
		UnicastRemoteObject.exportObject(this, 0);
	}
	
	//crear arreglo para respaldo de bd
	private ArrayList<Persona> bd_empleados_copia = new ArrayList<>();
	
	public void conectarBD() {
		Connection connection = null;
		Statement query = null;
		//PreparedStatement test = null;
		ResultSet resultados = null;
		
		
		try {
			String url = "jdbc:mysql://localhost:3306/ici4344";
			String username = "root";
			String password_BD = "";
			
			connection = DriverManager.getConnection(url, username, password_BD);
			
			//TODO Metodos con la BD
			query = connection.createStatement();
			String sql = "SELECT * FROM bd_acme";
			//INSERT para agregar datos a la BD, PreparedStatement
			
			resultados = query.executeQuery(sql);
			
			while (resultados.next()) {
				int id = resultados.getInt("id_empleado");
				String nombre = resultados.getString("nombre");
				String apellido = resultados.getString("apellido1");
				int creditos = resultados.getInt("creditos");
				String tipo_jornada = resultados.getString("tipo_jornada");
				
				Persona newPersona = new Persona(id, nombre, apellido, creditos, tipo_jornada);
				
				bd_empleados_copia.add(newPersona);
				
			}
			connection.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("No se pudo conectar a la BD :C");
		}
	}
	
	@Override
	public ArrayList<Persona> getPersonas() throws RemoteException {
		// TODO Auto-generated method stub
		return bd_empleados_copia;
	}

	@Override
	public String getDataFromApi() {
		String output = null;
		 
		try {
            // URL de la API REST, el listado de APIs públicas está en: 
			// https://github.com/juanbrujo/listado-apis-publicas-en-chile
            URL apiUrl = new URL("https://mindicador.cl/api");

            // Abre la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Configura la solicitud (método GET en este ejemplo)
            connection.setRequestMethod("GET");

            // Obtiene el código de respuesta
            int responseCode = connection.getResponseCode();

            // Procesa la respuesta si es exitosa
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta del servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Cierra la conexión y muestra la respuesta
                in.close();
                output = response.toString();
            } else {
                System.out.println("Error al conectar a la API. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		//Como resultado tenemos un String output que contiene el JSON de la respuesta de la API
		return output;
	}

	@Override
	public Object[] getUF() throws RemoteException {
		String output = null;
		 
		try {
            // URL de la API REST, el listado de APIs públicas está en: 
			// https://github.com/juanbrujo/listado-apis-publicas-en-chile
            URL apiUrl = new URL("https://mindicador.cl/api");

            // Abre la conexión HTTP
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            // Configura la solicitud (método GET en este ejemplo)
            connection.setRequestMethod("GET");

            // Obtiene el código de respuesta
            int responseCode = connection.getResponseCode();

            // Procesa la respuesta si es exitosa
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lee la respuesta del servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Cierra la conexión y muestra la respuesta
                in.close();
                output = response.toString();
            } else {
                System.out.println("Error al conectar a la API. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		//Como resultado tenemos un String output que contiene el JSON de la respuesta de la API
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			JsonNode jsonNode = objectMapper.readTree(output);
			String codigo = jsonNode.get("uf").get("codigo").asText(); 
			String nombre = jsonNode.get("uf").get("nombre").asText();
			String fecha = jsonNode.get("uf").get("fecha").asText();
			String unidad_medida = jsonNode.get("uf").get("unidad_medida").asText();
			double valor = jsonNode.get("uf").get("valor").asDouble();
			
			return new Object[] {codigo, nombre, fecha, unidad_medida, valor};
			
		}catch (JsonMappingException e) {
			e.printStackTrace();
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
