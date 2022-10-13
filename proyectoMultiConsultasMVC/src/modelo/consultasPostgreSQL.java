package modelo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** consultasPostgreSQL - Definición de las posibles consultas a ejecutar sobre la BD.
 * @author garfe
 *
 */
public class consultasPostgreSQL {
	
	/** selectAllAlumnos - Consulta sobre la tabla alumnos.
	 * SELECT * FROM \"proyectoEclipse\".\"alumnos\"
	 * @author garfe
	 * @param Connection conexionGenerada
	 * @return ArrayList<dtoAlumno>
	 */
	public static ArrayList<dtoAlumno> selectAllAlumnos(Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Entra en selectAllAlumnos");
		Statement declaracionSQL = null;
		ResultSet resultadoConsulta = null;
		ArrayList<dtoAlumno> listAlumnos = new ArrayList<>();
		
		try {
			
			//Se abre una declaración
			declaracionSQL = conexionGenerada.createStatement();
			//Se define la consulta de la declaración y se ejecuta
			resultadoConsulta = declaracionSQL.executeQuery("SELECT * FROM \"proyectoEclipse\".\"alumnos\"");
		    
			//Llamada a la conversión a dtoAlumno
			listAlumnos = dtoADto.resultsetAdtoAlumno(resultadoConsulta);
			int i = listAlumnos.size();
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Número alumnos: "+i);
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-selectAllAlumnos] Cierre declaración y resultado");				
		    resultadoConsulta.close();
		    declaracionSQL.close();
		    conexionGenerada.close();
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-conexionPostgresql-main] Error generando la declaracionSQL: " + e);
			return listAlumnos;
			
		}
		return listAlumnos;
		
	}
	
	/** insertNuevoAlumno - Insert informado en el parámetro consulta sobre la tabla alumnos.
	 * @author garfe
	 * @param String consulta
	 * @param Connection conexionGenerada
	 * @return void
	 */
	public static void insertNuevoAlumno(String consulta, Connection conexionGenerada) {
		
		System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Entra en insertNuevoAlumno");
		Statement declaracionSQL = null;
		
		try {
			
			declaracionSQL = conexionGenerada.createStatement();
			declaracionSQL.execute(consulta);
			declaracionSQL.close();
			
			System.out.println("[INFORMACIÓN-consultasPostgreSQL-insertNuevoAlumno] Cierre declaración.");
			
		} catch (SQLException e) {
			
			System.out.println("[ERROR-consultasPostgreSQL-insertNuevoAlumno] Error al insertar alumno: " + e);
		
		}
		
	}

}
