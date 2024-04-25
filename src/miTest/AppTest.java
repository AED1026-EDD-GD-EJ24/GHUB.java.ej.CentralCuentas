package miTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import datos.Estudiante;
import datos.Fecha;
import datos.LectorArchivo;
import datos.Recurso;
import datos.Prestamo;

import datos.Lista;
import datos.Nodo;
import negocios.Universidad;

import datos.PosicionIlegalException;

class AppTest {
	private Lista<Estudiante> estudiantes;
	private Lista<Recurso> recursos;
	
	
	private Universidad universidad;
	private Estudiante estudiante;
	private Recurso recurso;
	
	@Test
	public void test_agregarEstudiante() throws PosicionIlegalException
	{
			universidad = new Universidad();
			
			
			boolean condicion = universidad.agregarEstudiante(20240006,"Maria Lourdes Armenta Lindoro",
					"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"Femenino","Sistemas");  
			assertTrue(condicion,"El estudiante No debe existir en la Universidad");
			
			condicion = universidad.agregarEstudiante(20240003,"Cecilia Aragon",
					"ceciliaa@itculiacan.edu.mx", new Fecha("06/12/2006"),"Femenino","Sistemas");
			assertFalse(condicion,"El estudiante debe existir en la Universidad");
					
	}
	@Test
	public void test_agregarRecurso() throws PosicionIlegalException{
		
		universidad = new Universidad();
		
		boolean condicion = universidad.agregarRecurso(100,"Guitarra eléctrica");
		assertTrue(condicion,"El Recurso se agregó con éxito");
		
		condicion = universidad.agregarRecurso(90,"Saxofon");
		
		assertFalse(condicion,"El Recurso No se pudo agregar");	
		
	}
	@Test
	public void test_buscarEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		
		estudiante = universidad.buscarEstudiante(20240001);
		assertEquals("Ana Lopez",estudiante.getNombre(),"El nombre no es el esperado");
	}
		
	@Test
	public void test_buscarRecurso() throws PosicionIlegalException{
		
		universidad = new Universidad();
		recurso = new Recurso();
		
		
		recurso = universidad.buscarRecurso(70);
		assertEquals("Pesitas de 500 grs",recurso.getNombre(),"El nombre es el esperado");	
		
	}
	@Test 
	public void test_prestarRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		recurso = new Recurso();
		
		recurso = universidad.buscarRecurso(70);
		assertTrue(recurso.isDisponible(),"El recurso debe estar disponible");
		
		assertTrue(universidad.prestarRecursos(20240001, 70), "El recurso fue prestado con exito");

		recurso = universidad.buscarRecurso(70); 
		assertFalse(recurso.isDisponible(),"El recurso ya no debe estar disponible");

		assertFalse(universidad.prestarRecursos(20240001, 70), "El recurso no puede ser prestado dos veces");
		
	}
	@Test
	public void test_consultarEstudianteTieneRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		
		assertTrue(universidad.prestarRecursos(20240001, 70), "El recurso 70 fue prestado con exito");
		
		estudiante=universidad.consultarEstudianteTieneRecurso(70);
		assertEquals("Ana Lopez",estudiante.getNombre(),"Fue prestado el recurso 70");
  		
		
	}
	@Test
	public void test_consultarRecursosDeUnEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiante = new Estudiante();
		recursos = new Lista<Recurso>();
		
		assertTrue(universidad.prestarRecursos(20240001, 70), "El recurso 70 fue prestado con exito");
		
		estudiante=universidad.consultarEstudianteTieneRecurso(70);
		assertEquals("Ana Lopez",estudiante.getNombre(),"Fue prestado el recurso 70");
		
		assertTrue(universidad.prestarRecursos(20240001, 10), "El recurso 10 fue prestado con exito");
		assertTrue(universidad.prestarRecursos(20240001, 20), "El recurso 20 fue prestado con exito");
		
		recursos=universidad.consultarRecursosDeUnEstudiante(20240001);
		assertEquals(3,recursos.getTamanio(),"Tiene 3 recursos prestados");
		
	}
	@Test
	public void test_devolverRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		recurso = new Recurso();
		
		assertTrue(universidad.prestarRecursos(20240001, 70), "El recurso 70 fue prestado con exito");
		recurso = universidad.buscarRecurso(70);
		assertFalse(recurso.isDisponible(),"El recurso ya no debe estar disponible");
		assertFalse(universidad.prestarRecursos(20240001, 70), "El recurso 70 No puede ser prestado dos veces");
		recurso = universidad.buscarRecurso(70);
		assertTrue(universidad.devolverRecurso(70),"El recurso 70 de devolvió con éxito");
		recurso = universidad.buscarRecurso(70);
		assertTrue(recurso.isDisponible(),"El recurso 70 debe estar disponible");
		assertTrue(universidad.prestarRecursos(20240001, 70), "El recurso 70 fue prestado con exito");

	
	}
	@Test
	public void test_eliminarRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
	
		assertFalse(universidad.eliminarRecurso(100),"No se puede eliminar un recurso que no existe");
		
		assertTrue(universidad.eliminarRecurso(40),"Se eliminó un recurso");
		
		assertNull(universidad.buscarRecurso(40),"El recurso ya no existe y regresa Nulo");
		
		
	}
	@Test
	public void test_eliminarEstudiante() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
		estudiantes = new  Lista<Estudiante>();
		
		assertFalse(universidad.eliminarEstudiante(123),"El estudiante no existe por lo que no se elimina");
		
		boolean condicion = universidad.agregarEstudiante(20230001,"Maria Lourdes Armenta Lindoro",
				"maria.al@culiacan.tecnm.mx", new Fecha("11/02/1968"),"F","Sistemas");  
		assertTrue(condicion,"El estudiante se agregó con éxito");
		assertTrue(universidad.eliminarEstudiante(20230001),"Se eliminó con éxito");
		
		assertTrue(universidad.eliminarEstudiante(20240001),"Se elimino con exito un estudiante con prestamo");
		assertTrue(universidad.eliminarEstudiante(20240002),"Se elimino con exito un estudiante con prestamo");
		
	}
	@Test
	public void test_buscarNombreRecurso() throws PosicionIlegalException{
		universidad = new Universidad();
		
		String nombre = universidad.buscarNombreRecurso(50);
		assertEquals("balón de futbol",nombre,"El nombre es el esperado");
	
	}
	@Test
	public void test_buscarNombreEstudiante() throws PosicionIlegalException{
        universidad = new Universidad();
		
		String nombre = universidad.buscarNombreEstudiante(20240001);
		assertEquals("Ana Lopez",nombre,"El nombre es el esperado");
		
	}
	@Test
	public void test_mostrarRecursos() throws PosicionIlegalException{
		universidad = new Universidad();
		recursos = new Lista<Recurso>();
		recursos.agregar(new Recurso(10,"Guitarra",true));
		recursos.agregar(new Recurso(20,"Violin",true));
		recursos.agregar(new Recurso(30,"Trompeta",true));
		recursos.agregar(new Recurso(40,"Bat de beisbol",true));
		recursos.agregar(new Recurso(50,"balón de futbol",true));
		System.out.println("Tamaño Recursos:"+recursos.getTamanio());
		
		
	}
	@Test 
	public void test_mostrarEstudiantes() throws PosicionIlegalException{
		universidad = new Universidad();
		estudiantes = new Lista<Estudiante>();
		estudiantes.agregar(new Estudiante(20231000,"Ana Lopez","email",
	            new Fecha("01/01/2000"),"Femenino","Sistemas"));
		estudiantes.agregar(new Estudiante(20231001,"Pedro Buelna","email",
		    new Fecha("24/12/1999"),"Masculino","Sistemas"));
		estudiantes.agregar(new Estudiante(20231002,"Cecilia Aragon","email",
		    new Fecha("06/12/1999"),"Femenimo","Sistemas"));
		estudiantes.agregar(new Estudiante(20232001,"Patricia Reyes","email",
		    new Fecha("30/01/2001"),"Femenimo","TICs"));
		estudiantes.agregar(new Estudiante(20232002,"Juan Sanchez","email",
		    new Fecha("30/05/2001"),"Masculino","TICs"));
		assertEquals(5,estudiantes.getTamanio(),"Deben existir 5 elementos de estudiantes");
		Estudiante est = estudiantes.getValor(0);
		
		
	}
	@Test
	public void test_listaMasTresPrestados() throws PosicionIlegalException{
		universidad = new Universidad();
		
		estudiantes = new Lista<Estudiante>();
		
		universidad.prestarRecursos(20240001, 10);
		universidad.prestarRecursos(20240001, 20);
		universidad.prestarRecursos(20240001, 30);
		universidad.prestarRecursos(20240001, 40);
		
		universidad.prestarRecursos(20240002, 50);
		universidad.prestarRecursos(20240002, 60);
		universidad.prestarRecursos(20240002, 70);
		universidad.prestarRecursos(20240002, 80);
		
		universidad.prestarRecursos(20240003, 90);
		
		estudiantes = universidad.mostrarEstudiantesMasDeTres();
		
		assertEquals(2,estudiantes.getTamanio(),"Existen 2 estudiantes con mas de 3 prestamos");
		
	}
	@Test
	public void testConstructorCargaRecursos() throws PosicionIlegalException {
        Universidad universidad = new Universidad();
		Lista<Recurso> recursos = universidad.mostrarRecursos();
        assertNotNull(recursos,"La lista de recursos no debería ser null después de la construcción");
        assertFalse(recursos.esVacia(),"La lista de recursos no debería estar vacía después de la construcción");
    }



	

}
