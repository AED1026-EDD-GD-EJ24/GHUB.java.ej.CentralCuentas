package datos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class LectorArchivo {

    public void leerArchivo( Lista <Estudiante> estudiantes, 
                             Lista <Recurso> recursos, 
                             Lista <Prestamo> prestamos) {
        String rutaArchivo = "/workspaces/GHUB.java.ej.CentralCuentas/recursos.txt";
        String rutaArchivoEstudiantes = "/workspaces/GHUB.java.ej.CentralCuentas/estudiantes.txt";

        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            //código faltante
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Scanner scannerEstudiantes = new Scanner(new File(rutaArchivoEstudiantes))) {
            //código faltante

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            

    }
}