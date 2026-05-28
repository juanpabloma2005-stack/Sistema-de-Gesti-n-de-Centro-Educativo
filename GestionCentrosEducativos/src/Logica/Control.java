/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jpma0
 */
public class Control {

    //Array con usuarios y contraseñas quemadas
    static  String usuarios [][]={
        {"administrador","admin2025"},
        {"docente","docente2025"}         
    };

    //almacen de datos tipo TXT
static final String[] archivos = {"cursos.txt", "aulas.txt", "docentes.txt", "estudiantes.txt"};
   

 // verifica el archivo 
    public static boolean existeEnArchivo(String archivo, String dato) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null)
                if (linea.equalsIgnoreCase(dato)) return true;
        } catch (IOException e) {}
        return false;
    }

    //agrega lineas de textos directamente en los txt
    public static void escribirArchivo(String archivo, String contenido) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo, true))) {
            escritor.write(contenido); escritor.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en " + archivo);
        }
    }

    //lee y muestra el contenido de los archivos
    public static void leerArchivo(String archivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea; int i = 1;
            System.out.println(""); 
            while ((linea = lector.readLine()) != null)                              
                System.out.println(i++ + ". " + linea);
        } catch (IOException e) {
            System.out.println("Archivo no encontrado o vacio.");
        }
    }

    
    //lee, enlista y muestra los datos del archivo
    public static List<String> leerArchivoALista(String archivo) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null)
                lista.add(linea);
        } catch (IOException e) {}
        return lista;
    }

    
    //remplaza el contenido que ya se encuentra en el archivo
    public static void sobrescribirArchivo(String archivo, List<String> datos) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo))) {
            for (String dato : datos) {
                escritor.write(dato); escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al sobrescribir " + archivo);
        }
    }
    
    //control para evitar choques en las asignaciones
    public static boolean verificarTraslape(String archivo, String docente, String aula, String horario, String[] estudiantes) {
          try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
          String linea;
         while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(" \\| ");
            if (partes.length >= 5) {
                String d = partes[1];         
                String a = partes[2];         
                String h = partes[3];         
                String[] estudiantesExistentes = partes[4].split(",");

                //traslape de docente
                if (d.equalsIgnoreCase(docente) && h.equalsIgnoreCase(horario)) {
                    return true;
                }

                //traslape de aula
                if (a.equalsIgnoreCase(aula) && h.equalsIgnoreCase(horario)) {
                    return true;
                }

                //traslape de estudiantes
                for (String estudianteNuevo : estudiantes) {
                    for (String estudianteExistente : estudiantesExistentes) {
                        if (estudianteNuevo.trim().equalsIgnoreCase(estudianteExistente.trim())
                            && h.equalsIgnoreCase(horario)) {
                            return true;
                        }
                    }
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}

    //organiza las notas en un array y saca el promedio
    public static double calcularPromedio(String[] reg) {
    double cotidiano = Double.parseDouble(reg[2]);
    double tareas = Double.parseDouble(reg[3]);
    double pruebas = Double.parseDouble(reg[4]);
    double proyecto = Double.parseDouble(reg[5]);
    return (cotidiano + tareas + pruebas + proyecto);
}
    
    
    //lee el archivo y enlista los estudiantes que ya estan asignados a un curso
    public static List<String> estudiantesAsignados(String curso) {
    List<String> lista = new ArrayList<>();
    try (BufferedReader lector = new BufferedReader(new FileReader("asignacionesCompletas.txt"))) {
        String linea;
        while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(" \\| ");
            if (partes.length == 5 && partes[0].equalsIgnoreCase(curso)) {
                String[] estudiantes = partes[4].split(",");
                for (String est : estudiantes) {
                    lista.add(est);
                }
            }
        }
    } catch (IOException e) {}
    return lista;
}
}
