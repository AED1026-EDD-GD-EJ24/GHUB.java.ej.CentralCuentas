package uI;

import java.util.Scanner;

import datos.Estudiante;
import datos.Fecha;
import datos.Lista;
import datos.Recurso;
import negocios.Universidad;
import negocios.Email;


public class MenuOpciones {
	static Scanner entrada = new  Scanner(System.in);
	private Universidad univ = new Universidad();
	
	public  void agregarRecurso() {
		
		try {
			Integer id;
			String nombre;
			
			do {
				
				System.out.println("Ingresar un ID de Recurso");
				System.out.println("El ID del Recurso no puede repetirse");
		
				System.out.print("ID:");
				id = entrada.nextInt();
				
			}while (!(univ.buscarRecurso(id) == null));
			entrada.nextLine();
			System.out.print("NOMBRE:");
			nombre = entrada.nextLine();
			
			univ.agregarRecurso(id, nombre);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	   
	public void eliminarRecurso() {
		try {
			Integer id;
			     
			do {
				
				System.out.println("Ingresar el Id de Recurso");
				System.out.println("El ID del Estudiante debe existir");
				
	
				System.out.print("Id:");
				id = entrada.nextInt();
				
		
			}while ((univ.buscarRecurso(id) == null));
			
			univ.eliminarRecurso(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	public void devolverRecurso() {
		try {
			Integer id;
			String nombre;
			do {
				
				System.out.print("Ingresar un ID de Recurso:");
				
				id = entrada.nextInt();
				
			} while ((univ.buscarRecurso(id) == null));
			
			nombre=univ.buscarNombreRecurso(id);
			System.out.println(" "+nombre);
			
			univ.devolverRecurso(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
	public void mostrarRecursos() {
		try {
			univ.mostrarRecursos();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	  
	}
    public  void agregarEstudiante() {
		
		try {
			Integer codigo;
			String nombre, email,sexo,programa;
			Fecha fechaNac;
			      
			do {
				
				System.out.println("Ingresar un nuevo Estudiante");
				System.out.println("El ID del Estudiante no puede repetirse");
			
				System.out.print("Código:");
				
				codigo = entrada.nextInt();
				
			}while (!(univ.buscarEstudiante(codigo) == null));
			
			entrada.nextLine();
			System.out.print("NOMBRE:");
			nombre = entrada.nextLine();
			try {
				System.out.print("CORREO:");
				email = entrada.nextLine();
				new Email(email);
			}
			catch (Exception e) {
				System.out.println("Correo no válido");
				System.out.println("Si desea agregar nuevamente un contacto");
				System.out.println("Seleccione la opción 1");
				return;
			}
			
			System.out.print("Fecha Nacimiento (MM/DD/AAAA):");
			String sFecha= entrada.nextLine();
			fechaNac = new Fecha(sFecha);
			System.out.print("Sexo:");
			sexo = entrada.nextLine();
			System.out.print("Programa:");
			programa = entrada.nextLine();
	
			univ.agregarEstudiante(codigo, nombre, email, fechaNac, sexo, programa);
			
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	   
	public void eliminarEstudiante() {
		try {
			Integer codigo;
			     
			do {
				
				System.out.print("Código:");
				codigo = entrada.nextInt();
				
			} while (univ.buscarEstudiante(codigo) == null);
			univ.eliminarEstudiante(codigo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void mostrarEstudiantes() {
		try {
			univ.mostrarEstudiantes();
	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	 
	}
    public  void prestarRecurso() {
		
		try {
			Integer codigo;
			Integer id;
			
			      
			do {
				
				System.out.print("Ingresar Código de Estudiante: ");
				codigo = entrada.nextInt();
				System.out.println("Nombre Estudiante: "+univ.buscarNombreEstudiante(codigo));
				
				
				System.out.print("Ingresar Id de Recurso: ");
				id = entrada.nextInt();
				System.out.println("Descripción Recurso: "+univ.buscarNombreRecurso(id));
		
				
			} while ((univ.buscarEstudiante(codigo) == null || 
					univ.buscarRecurso(id)==null));
			
			univ.prestarRecursos(codigo, id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
    public  void ConsultarRecursoPrestado() {
		
		try {
			Integer id;
			String nombre;
			do {
				
				System.out.print("Ingresar  ID Recurso:");
			
				id = entrada.nextInt();
		
			}while ((univ.buscarRecurso(id) == null));
			
			nombre= univ.buscarNombreRecurso(id);
				System.out.print("\t"+nombre+" ");
			
			univ.consultarEstudianteTieneRecurso(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    public  void consultarRecursosEstudiante() {
		try {
			Integer codigo;
			String nombre;
			do {
				
				System.out.print("Ingresar código Estudiante: ");
				codigo = entrada.nextInt();
				
			} while ((univ.buscarEstudiante(codigo) == null));
			
			nombre = univ.buscarNombreEstudiante(codigo);
			System.out.println("\t"+nombre);
			
			Lista <Recurso> recursos = univ.consultarRecursosDeUnEstudiante(codigo);
			for (int i=0;i<recursos.getTamanio();i++) {
				System.out.println(recursos.getValor(i));
			}
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
    }
    public  void consultarRecursosEstudianteMasDeTres() {
		try {
			 Lista<Estudiante> prestamosMasDeTres=new Lista<Estudiante>();
			 prestamosMasDeTres=univ.mostrarEstudiantesMasDeTres();
			 if (prestamosMasDeTres.getTamanio()==0)
				 System.out.println("*** No Existe ningún estudiante con mas de TRES préstamos ***");
			 else
				 System.out.println("*** Estudiantes con mas de Tres Préstamos ***");
				 
			
			 for(int i=0;i<prestamosMasDeTres.getTamanio();i++) {
				 System.out.println("Estudiante: "+
			 prestamosMasDeTres.getValor(i).getNombre()+" "+prestamosMasDeTres.getValor(i).getCodigo()+" "+
						                           prestamosMasDeTres.getValor(i).getEmail()+" "+
						                           prestamosMasDeTres.getValor(i).getPrograma()+" "+
						                           prestamosMasDeTres.getValor(i).getSexo()+" "+
						                           prestamosMasDeTres.getValor(i).getFechaNac()+" ");
			 }
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
    }
	
}

	

	

