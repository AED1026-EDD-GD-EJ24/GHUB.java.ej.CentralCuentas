package datos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class LectorArchivo {

    public void leerArchivo( Lista <Estudiante> estudiantes, 
                             Lista <Recurso> recursos, 
                             Lista <Prestamo> prestamos) {
        String rutaArchivo = "/workspaces/GHUB.java.ag.CentralCuentas/recursos.txt";
        String rutaArchivoEstudiantes = "/workspaces/GHUB.java.ag.CentralCuentas/estudiantes.txt";

        try (Scanner scanner = new Scanner(new File(rutaArchivo))) {
            scanner.useDelimiter(",\\s*");

            while (scanner.hasNext()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");

                
                int id = Integer.parseInt(campos[0].replaceAll("\"", ""));
                String nombre = campos[1].replaceAll("\"", "");
                boolean disponible = Boolean.parseBoolean(campos[2].replaceAll("\"", ""));

                Recurso recurso = new Recurso(id, nombre, disponible);
                recursos.agregar(recurso);
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Scanner scannerEstudiantes = new Scanner(new File(rutaArchivoEstudiantes))) {
            scannerEstudiantes.useDelimiter(",\\s*");

            while (scannerEstudiantes.hasNext()) {
                String linea = scannerEstudiantes.nextLine();
                String[] campos = linea.split(",");

                int codigo = Integer.parseInt(campos[0].replaceAll("\"", ""));
                String nombre = campos[1].replaceAll("\"", "");
                String email = campos[2].replaceAll("\"", "");
                String fechaNac = campos[3].replaceAll("\"", "");
                String sexo = campos[4].replaceAll("\"", "");
                String programa = campos[5].replaceAll("\"", "");
               
                Estudiante est = new Estudiante(codigo, nombre, email, new Fecha(fechaNac), sexo, programa);
                estudiantes.agregar(est);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
            

    }
}