package negocios;
import datos.Estudiante;
import datos.Lista;
import datos.PosicionIlegalException;
import datos.Prestamo;
import datos.Recurso;
import datos.Fecha;

import datos.LectorArchivo;

public class Universidad {
	private Lista<Estudiante> estudiantes;
	private Lista<Recurso> recursos;
	private Lista<Prestamo> prestamos;
	public Universidad() {
		estudiantes = new Lista<Estudiante>();
		recursos = new Lista<Recurso>();
		prestamos = new Lista<Prestamo>();
		//invocar al archivo de recursos
		LectorArchivo lector = new LectorArchivo();
        lector.leerArchivo(estudiantes, recursos, prestamos);
		
	}

	public boolean agregarEstudiante(int codigo, String nombre,
			String email,Fecha fechaNac, String sexo, String programa)
					throws PosicionIlegalException{
		//código faltante
			
	}
	public Estudiante buscarEstudiante(int codigo) throws PosicionIlegalException{
		//código faltante
	}
	public boolean agregarRecurso(int id, String nombre) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		//código faltante
	}
	public Recurso buscarRecurso(int id) throws PosicionIlegalException{
		//código faltante
	}
	public boolean prestarRecursos(int codigo, int id) throws PosicionIlegalException{
		//código faltante
		
	}
	public Estudiante consultarEstudianteTieneRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec==null) {
			System.out.println("Recurso no existen");
			return null;
		}
		if(rec.isDisponible()) {
			System.out.println("El recurso "+rec+" esta disponible");
			return null;
		}
		//código faltante
	}
	public Lista<Recurso> consultarRecursosDeUnEstudiante(int codigo) throws
	   PosicionIlegalException {
		Estudiante est = buscarEstudiante(codigo);
		if(est == null) {
			System.out.println("Estudiante no existe");
			return null;
		}
		Lista<Recurso> recursosprestados = new Lista<Recurso>();
		//código faltante
		return recursosprestados;
		
	}
	
	public boolean devolverRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if (rec==null){
			System.out.println("Recurso no existe");
			return false;
		}
		//código faltante
	}
	public boolean eliminarRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		//buscar el recurso
		//código faltante
		//Borrar el recurso de los prestamos
		//código falltate
		//Borrar el recurso de los recursos
		//código faltante
	}
	public boolean eliminarEstudiante(int codigo) throws PosicionIlegalException{
		Estudiante est = buscarEstudiante(codigo);
		if (est==null) {
			System.out.println("Estudiante no existe");
			return false;
		}
		//Borrar el estudiante de los prestamos
		
		//código faltantw
			
			
		}
		
		
		//Borrar el estudiantes de estudiantes
		//código faltante
		
		System.out.println("El estudiante "+est+ " fue eliminado"
				+ " satisfactoriamente");
		return true;
	}
	public String buscarNombreRecurso(int id) throws PosicionIlegalException{
		for(int i=0; i<recursos.getTamanio();i++) {
			Recurso rec = recursos.getValor(i);
			if(rec.getId()==id) {
				return rec.getNombre();
			}
		}
		return null;
	}
	public String buscarNombreEstudiante(int codigo) throws PosicionIlegalException{
		//código faltante
	}
	
	public Lista<Recurso> mostrarRecursos() throws PosicionIlegalException{
		//código faltante
	}
	
	public Lista<Estudiante> mostrarEstudiantes() throws PosicionIlegalException {
		//código faltante
	}
	/**
	 * 
	 * @return una lista de los estudiantes que cumplan con la condición de tener mas
	 *       de tres préstamos
	 * @throws PosicionIlegalException
	 */
	
	public Lista<Estudiante> mostrarEstudiantesMasDeTres() throws PosicionIlegalException {
		 //código faltante
	}
	
}
		
	
	





