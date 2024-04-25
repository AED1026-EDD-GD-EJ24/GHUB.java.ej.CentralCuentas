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
		//código faltante
	  
	}
    public  void agregarEstudiante() {
		
		try {
			Integer codigo;
			String nombre, email,sexo,programa;
			Fecha fechaNac;
			      
			do {
				
				//colocar código faltante
				
			}while (!(univ.buscarEstudiante(codigo) == null));
			
			entrada.nextLine();
			System.out.print("NOMBRE:");
			nombre = entrada.nextLine();
			try {
				System.out.print("CORREO:");
				email = entrada.nextLine();
				//colocar código faltante
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
	
			//agregar estudiante
			//colocar código faltante
			
	
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
				
			} while () == null); //colocar código faltante
			univ.eliminarEstudiante(codigo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void mostrarEstudiantes() {
		//colocar código faltante
	 
	}
    public  void prestarRecurso() {
		
		//colocar código faltante
		
	}
	
    public  void ConsultarRecursoPrestado() {
		
		//colocar código faltante
		
	}
    public  void consultarRecursosEstudiante() {
		//colocar código faltante
	
    }
    public  void consultarRecursosEstudianteMasDeTres() {
		//colocar código faltante
	
    }
	
}

	

	

