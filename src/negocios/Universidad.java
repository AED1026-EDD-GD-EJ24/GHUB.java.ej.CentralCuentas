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
		Estudiante est = buscarEstudiante(codigo);
		if (est == null) {
			estudiantes.agregar(new Estudiante(codigo, nombre,
					email, fechaNac, sexo, programa));
			return true;
		}
		return false;
			
	}
	public Estudiante buscarEstudiante(int codigo) throws PosicionIlegalException{
		for (int i=0;i<estudiantes.getTamanio();i++) {
			Estudiante est = estudiantes.getValor(i);
			if (est.getCodigo() == codigo) 
				return est;	
		}
		return null;
	}
	public boolean agregarRecurso(int id, String nombre) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec == null) {
			recursos.agregar(new Recurso(id, nombre, true));
			return true;
		}
		return false;
	}
	public Recurso buscarRecurso(int id) throws PosicionIlegalException{
		for(int i=0; i<recursos.getTamanio();i++) {
			Recurso rec = recursos.getValor(i);
			if(rec.getId()==id) {
				return rec;
			}
		}
		return null;
	}
	public boolean prestarRecursos(int codigo, int id) throws PosicionIlegalException{
		Estudiante est = buscarEstudiante(codigo);
		String nombre = buscarNombreEstudiante(codigo);
		System.out.print(nombre+" ");
		Recurso rec = buscarRecurso(id);
		nombre = buscarNombreRecurso(id);
		System.out.print(nombre+" ");
		if(est ==null || rec==null) {
			System.out.println("Estudiante o recurso no existen");
			return false;
		}
		if(!rec.isDisponible()) {
			System.out.println("El recurso "+rec +" ya esta prestado "+
		      "a otro estudiante");
			return false;
		}
		Prestamo pres=new Prestamo(rec, est);
		pres.setFechaPrestamo(new Fecha());
		rec.setDisponible(false);
		prestamos.agregar(pres);
		System.out.println("El recurso "+rec+ "fue prestado al estudiante "+est);
		return true;
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
		for(int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if(rec.getId()==id && !rec.isDisponible()) {
				System.out.println("El recurso "+rec+" esta prestado al estudiante "+
			           pres.getEstudiante() );
				return pres.getEstudiante();
			}
		}
		return null;
	}
	public Lista<Recurso> consultarRecursosDeUnEstudiante(int codigo) throws
	   PosicionIlegalException {
		Estudiante est = buscarEstudiante(codigo);
		if(est == null) {
			System.out.println("Estudiante no existe");
			return null;
		}
		Lista<Recurso> recursosprestados = new Lista<Recurso>();
		for(int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if (pres.getEstudiante().getCodigo() ==codigo && 
					!pres.getRecurso().isDisponible()) {
				recursosprestados.agregar(pres.getRecurso());
			}
		}
		return recursosprestados;
		
	}
	
	public boolean devolverRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if (rec==null){
			System.out.println("Recurso no existe");
			return false;
		}
		if(rec.isDisponible()) {
			System.out.println("El recurso "+rec+" no se puede devolver "+
		   " porque esta disponible");
			return false;
		}
		for (int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres=prestamos.getValor(i);
			if(pres.getRecurso().getId()==id && pres.getFechaDevolucion()==null) {
				pres.setFechaDevolucion(new Fecha());
				rec.setDisponible(true);
				System.out.println("Se ha devuelto el recurso "+
				   rec+ "por parte del estudiantes "+
						pres.getEstudiante()+ "satisfactoriamente");
				return true;
			}
		}
		return false;
	}
	public boolean eliminarRecurso(int id) throws PosicionIlegalException{
		Recurso rec = buscarRecurso(id);
		if(rec ==null) {
			System.out.println("Recursos no existen");
			return false;
		}
		//Borrar el recurso de los prestamos
		for(int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres = prestamos.getValor(i);
			if(pres.getRecurso().getId()==id) {
				prestamos.remover(i);
			}
		}
		//Borrar el recurso de los recursos
		for(int i=0;i<recursos.getTamanio();i++) {
			if(recursos.getValor(i).getId() ==id) {
				recursos.remover(i);
			}
		}
		System.out.println("El recurso "+rec+" fue eliminado satisfactoriamente");
		return true;
	}
	public boolean eliminarEstudiante(int codigo) throws PosicionIlegalException{
		Estudiante est = buscarEstudiante(codigo);
		if (est==null) {
			System.out.println("Estudiante no existe");
			return false;
		}
		//Borrar el estudiante de los prestamos
		
		for (int i=0;i<prestamos.getTamanio();i++) {
			Prestamo pres= prestamos.getValor(i);
			if(pres.getEstudiante().getCodigo() == codigo) {
				prestamos.remover(i);
			}
			
			
		}
		
		
		//Borrar el estudiantes de estudiantes
		for(int i=0; i<estudiantes.getTamanio();i++) {
			if(estudiantes.getValor(i).getCodigo() == codigo) {
				estudiantes.remover(i);
			}
		}
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
		for(int i=0; i<estudiantes.getTamanio();i++) {
			Estudiante est = estudiantes.getValor(i);
			if(est.getCodigo()==codigo) {
				return est.getNombre();
			}
		}
		return null;
	}
	
	public Lista<Recurso> mostrarRecursos() throws PosicionIlegalException{
		for (int i=0;i<recursos.getTamanio();i++) {
			System.out.println("Id: "+recursos.getValor(i).getId()+
					      "  Descripcion: "+recursos.getValor(i).getNombre() );
					     
		}
		return recursos;
	}
	
	public Lista<Estudiante> mostrarEstudiantes() throws PosicionIlegalException {
		for (int i=0;i<estudiantes.getTamanio();i++) {
			System.out.println("Cód: "+estudiantes.getValor(i).getCodigo()+
					      "  Nom: "+estudiantes.getValor(i).getNombre() +
						  "  Email: "+estudiantes.getValor(i).getEmail() +
						  "  Fec: "+estudiantes.getValor(i).getFechaNac()+
						  "  Gén: "+estudiantes.getValor(i).getSexo()+
						  "  Prog: "+estudiantes.getValor(i).getPrograma() );
						  
		}		
		return estudiantes;
	}
	/**
	 * 
	 * @return una lista de los estudiantes que cumplan con la condición de tener mas
	 *       de tres préstamos
	 * @throws PosicionIlegalException
	 */
	
	public Lista<Estudiante> mostrarEstudiantesMasDeTres() throws PosicionIlegalException {
		 Lista<Estudiante> prestamosMasDeTres=new Lista<Estudiante>();
		
		for (int i=0;i<estudiantes.getTamanio();i++) {
			
			 Estudiante estudiante = estudiantes.getValor(i);
			 int contador = 0;
			 for(int j=0;j<prestamos.getTamanio();j++) {
				 
				 if(estudiante.equals(prestamos.getValor(j).getEstudiante())) {
					 //Si el recurso del estudiante ya esta devuelto, no lo considero
					 if (prestamos.getValor(j).getFechaDevolucion() == null)
					    contador++;
				 }		 
				 
			 }
			 if (contador>3)
			 {
				 prestamosMasDeTres.agregar(estudiante);
			 }
			
		}	
		
		return prestamosMasDeTres;
	}
	
}
		
	
	





