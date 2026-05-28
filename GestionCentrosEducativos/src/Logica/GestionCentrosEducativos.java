/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jpma0
 */
public class GestionCentrosEducativos {

    /**
     * @param args the command line arguments
     */

    static Scanner leer = new Scanner(System.in);
    static Control servicio = new Control();

    //Metodo de inicio de sesion 
    public static void logIn (){
    int intentos= 0;
    boolean acceso = false;
    
    while (intentos <3){
        for (int i = 0; i < 22; i++) System.out.println();
        System.out.println("------------------");
        System.out.println("| Inicio de sesion|");
        System.out.println("------------------");
        System.out.println("");
        System.out.print("Ingrese el Usuario: ");
        String usuario = leer.nextLine();
        System.out.print("Ingrese la contrasenia de " + usuario + ": ");
        String contraseña = leer.nextLine();
        
        if ((usuario.equals(servicio.usuarios [0][0]) && contraseña.equals(servicio.usuarios[0][1])) ||
            (usuario.equals(servicio.usuarios[1][0]) && contraseña.equals(servicio.usuarios[1][1]))){
            acceso = true;
            
        if (usuario.equals("administrador")){
            menuAdministrador();
           
        }else{
            menuDocente();
        }
        break;
        
        }else {
            intentos++;
            System.out.println("Datos incorrectos. Intento " + intentos + " de 3");
        }
    }
    if (!acceso){
        System.out.println("Ha excedido el limite de intentos. Cerrando Sistema...");
    }
    }
    
    //Metodo para generar el menú del administrador.
    public static void menuAdministrador(){
    int opcion=0;
     do {
          for (int i = 0; i < 20; i++) System.out.println();
            System.out.println("---------------------------");
            System.out.println("|    MENU ADMINISTRADOR    |");
            System.out.println("---------------------------");
            System.out.println("1. Gestionar Cursos");
            System.out.println("2. Gestionar Aulas");
            System.out.println("3. Gestionar Docentes");
            System.out.println("4. Gestionar Estudiantes");
            System.out.println("5. asignar cursos");
            System.out.println("6. pre-cargar datos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leer.nextInt(); leer.nextLine();

            switch (opcion) {
                case 1: crud("Curso", servicio.archivos[0]); 
                break;
                case 2: crud("Aula", servicio.archivos[1]); 
                break;
                case 3: crud("Docente", servicio.archivos[2]); 
                break;
                case 4: crud("Estudiante", servicio.archivos[3]); 
                break;
                case 5: asignarCursoCompleto(); 
                break;
                case 6: cargarDatos();
                break;
                case 7: System.out.println("Saliendo..."); logIn();
                break;
                default: System.out.println("Opción inválida.");
    }
     }
     while (opcion <7);}
   
    //Metodo para crear,leer,editar y eliminar elementos
    public static void crud(String tipo, String archivo) {
        int opcion;
        do {
            for (int i=0; i<5; i++) System.out.println();
            System.out.println("--- Gestion de " + tipo + "s ---");
            System.out.println();
            System.out.println("1. Agregar");
            System.out.println("2. Ver");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Opcion: ");
            opcion = leer.nextInt(); leer.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.println();
                    System.out.print("Ingrese el nombre del " + tipo + ": ");
                    String nuevo = leer.nextLine();
                    if (!servicio.existeEnArchivo(archivo, nuevo)) {
                        servicio.escribirArchivo(archivo, nuevo);
                        System.out.println(tipo + " agregado correctamente.");
                    } else {
                        System.out.println(tipo + " ya existe.");
                    }
                    break;
                case 2: System.out.println("--- listado de "+ tipo + "s ---"); servicio.leerArchivo(archivo); 
                break;
                case 3: System.out.println("--- Edicion de " + tipo + "s ---"); editarElemento(archivo);
                break;
                case 4: System.out.println("--- Eliminar " + tipo + "s ---"); eliminarElemento(archivo); 
                break;
                case 5: System.out.println("Regresando a Menu Administrador...");
            }
        } while (opcion < 5);
    }
    
    //metodo para modificar los elementos (cursos, aulas, docentes, estudiantes)
    public static void editarElemento(String archivo) {
        List<String> elementos = servicio.leerArchivoALista(archivo);
        System.out.println();
        if (elementos.isEmpty()) {
            System.out.println("Archivo vacio.");
            return;
        }
        for (int i = 0; i < elementos.size(); i++)
            System.out.println((i + 1) + ". " + elementos.get(i));             System.out.println();
        System.out.print("Seleccione el numero a editar: ");
        int fila = leer.nextInt(); leer.nextLine();
        if (fila < 1 || fila > elementos.size()) {
            System.out.println("Numero invalido.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        String nuevoValor = leer.nextLine();
        elementos.set(fila - 1, nuevoValor);
        servicio.sobrescribirArchivo(archivo, elementos);
        System.out.println("Elemento editado.");
    }

    //metodo para eliminar los elementos
    public static void eliminarElemento(String archivo) {
        List<String> elementos = servicio.leerArchivoALista(archivo);
        System.out.println();
        if (elementos.isEmpty()) {
            System.out.println("Archivo vacio.");
            return;
        }
        for (int i = 0; i < elementos.size(); i++)
            System.out.println((i + 1) + ". " + elementos.get(i));             System.out.println();
        System.out.print("Seleccione el numero a eliminar: ");
        int lista = leer.nextInt(); leer.nextLine();
        if (lista < 1 || lista > elementos.size()) {
            System.out.println("Numero invalido.");
            return;
        }
        elementos.remove(lista - 1);
        servicio.sobrescribirArchivo(archivo, elementos);
        System.out.println("Elemento eliminado.");
    }
    
    //metodo para asignar curso, aula, docente, horario y estudiantes
    public static void asignarCursoCompleto() {
    System.out.println("--- Asignacion completa de cursos ---");

    System.out.print("Curso: ");
    String curso = leer.nextLine();
    if (!servicio.existeEnArchivo("cursos.txt", curso)) {
        System.out.println("Curso no existe.");
        return;
    }

    System.out.print("Docente: ");
    String docente = leer.nextLine();
    if (!servicio.existeEnArchivo("docentes.txt", docente)) {
        System.out.println("Docente no existe.");
        return;
    }

    System.out.print("Aula: ");
    String aula = leer.nextLine();
    if (!servicio.existeEnArchivo("aulas.txt", aula)) {
        System.out.println("Aula no existe.");
        return;
    }

    System.out.print("Horario (ej: Lunes 6:00pm): ");
    String horario = leer.nextLine(); 

    // Asignar estudiantes
    List<String> estudiantes = new ArrayList<>();
    while (true) {
        System.out.print("Ingrese nombre del estudiante (o 'fin' para terminar): ");
        String estudiante = leer.nextLine();
        if (estudiante.equalsIgnoreCase("fin")) break;
        if (!servicio.existeEnArchivo("estudiantes.txt", estudiante)) {
            System.out.println("Estudiante no registrado.");
        } else {
            if (!estudiantes.contains(estudiante))
                estudiantes.add(estudiante);
            else
                System.out.println("Ya fue agregado.");
        }
    }

    if (estudiantes.isEmpty()) {
        System.out.println("No se asigno ningun estudiante. No se guardo la asignacion");
        return;
    }
    
if (servicio.verificarTraslape("asignacionesCompletas.txt", docente, aula, horario, estudiantes.toArray(new String[0]))) {
    System.out.println("Ya hay una asignacion con ese docente, aula o estudiante en ese horario.");
    return;
}
    String linea = curso + " | " + docente + " | " + aula + " | " + horario + " | " + String.join(", ", estudiantes);

    servicio.escribirArchivo("asignacionesCompletas.txt", linea);
    System.out.println("Curso asignado correctamente.");
}   
    
    //metodo que pre-carga de datos
    public static void cargarDatos() {
    System.out.println("Cargando datos...");

    // Datos para cursos.txt
    servicio.sobrescribirArchivo("cursos.txt", new ArrayList<>()); // Vacía el archivo
    servicio.escribirArchivo("cursos.txt", "Matematicas");
    servicio.escribirArchivo("cursos.txt", "Ciencias");
    servicio.escribirArchivo("cursos.txt", "Espanol");
    servicio.escribirArchivo("cursos.txt", "Estudios Sociales");
    servicio.escribirArchivo("cursos.txt", "Ingles");
    servicio.escribirArchivo("cursos.txt", "Musica");
    servicio.escribirArchivo("cursos.txt", "Religion");
    servicio.escribirArchivo("cursos.txt", "Informatica");
    servicio.escribirArchivo("cursos.txt", "Orientacion");
    
    // Datos para aulas.txt
    servicio.sobrescribirArchivo("aulas.txt", new ArrayList<>()); 
    servicio.escribirArchivo("aulas.txt", "Aula 100");
    servicio.escribirArchivo("aulas.txt", "Aula 101");
    servicio.escribirArchivo("aulas.txt", "Aula 102");
    servicio.escribirArchivo("aulas.txt", "Aula 103");
    servicio.escribirArchivo("aulas.txt", "Aula 104");
    servicio.escribirArchivo("aulas.txt", "Aula 105");
    servicio.escribirArchivo("aulas.txt", "Aula 106");
    servicio.escribirArchivo("aulas.txt", "Aula 107");
    servicio.escribirArchivo("aulas.txt", "Aula 108");
    servicio.escribirArchivo("aulas.txt", "Aula 109");

    // Datos para docentes.txt
    servicio.sobrescribirArchivo("docentes.txt", new ArrayList<>()); 
    servicio.escribirArchivo("docentes.txt", "Miguel Hernandez");
    servicio.escribirArchivo("docentes.txt", "Joaquin Morera");
    servicio.escribirArchivo("docentes.txt", "Jorge Rodriguez");
    servicio.escribirArchivo("docentes.txt", "Lucia Lopez");
    servicio.escribirArchivo("docentes.txt", "Carlos Garcia");
    servicio.escribirArchivo("docentes.txt", "Brayan Rodriguez");
    servicio.escribirArchivo("docentes.txt", "Dahiana Lopez");
    servicio.escribirArchivo("docentes.txt", "Hugo Garcia");
    servicio.escribirArchivo("docentes.txt", "Jennifer Murillo");
    servicio.escribirArchivo("docentes.txt", "Anthony Hernandez");

    // Datos para estudiantes.txt
    servicio.sobrescribirArchivo("estudiantes.txt", new ArrayList<>()); 
    servicio.escribirArchivo("estudiantes.txt", "Juan Perez");
    servicio.escribirArchivo("estudiantes.txt", "Maria Gomez");
    servicio.escribirArchivo("estudiantes.txt", "Carlos Ruiz");
    servicio.escribirArchivo("estudiantes.txt", "Ana Torres");
    servicio.escribirArchivo("estudiantes.txt", "Laura Diaz");
    servicio.escribirArchivo("estudiantes.txt", "Kendall Gomez");
    servicio.escribirArchivo("estudiantes.txt", "Ricardo Munos");
    servicio.escribirArchivo("estudiantes.txt", "Alex Aragon");
    servicio.escribirArchivo("estudiantes.txt", "Lorena Munguia");
    servicio.escribirArchivo("estudiantes.txt", "Pablo Murillo");

    // Datos para asignacionesCompletas.txt
    servicio.sobrescribirArchivo("asignacionesCompletas.txt", new ArrayList<>()); 
    servicio.escribirArchivo("asignacionesCompletas.txt", "Matematicas | Miguel Hernandez | Aula 101 | Lunes 6:00pm | Juan Perez,Maria Gomez");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Estudios Sociales | Joaquin Morera | Aula 102 | Martes 1:00pm | Carlos Ruiz,Ana Torres");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Ingles | Jorge Rodriguez | Aula 103 | Miercoles 8:00am | Laura Diaz, Kendall Gomez");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Espanol | Lucia Lopez | Aula 104 | Jueves 8:00am | Ricardo Munos, Alex Aragón");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Informatica | Carlos Garcia | Aula 105 | Viernes 9:00am | Lorena Munguia, Pablo Murillo");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Ciencias | Brayan Rodriguez | Aula 106 | Lunes 11:00am | Juan Perez, Maria Gomez");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Religion | Dahiana Lopez | Aula 107 | Martes 12:00pm | Carlos Ruiz, Ana Torres");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Orientacion | Hugo Garcia | Aula 108 | Miercoles 3:00pm | Laura Diaz, Kendall Gomez");
    servicio.escribirArchivo("asignacionesCompletas.txt", "Musica | Jennifer Murillo | Aula 109 | Jueves 12:00pm | Ricardo Munos, Alex Aragon");
    }
    
    
    //Metodo para generar el menú de docente.
    public static void menuDocente(){
    int opcion;
    do {
        for (int i = 0; i < 5; i++) System.out.println();
        System.out.println("    -------------------");
        System.out.println("    |   MENU DOCENTE  |");
        System.out.println("    -------------------");
        System.out.println("1. Ingresar notas de estudiantes");
        System.out.println("2. Ver estadisticas del grupo");
        System.out.println("3. Ver calificaciones por rubro");
        System.out.println("4. Editar notas de un estudiante");
        System.out.println("5. Eliminar notas de un estudiante");
        System.out.println("6. Pre-cargar notas");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion(1-7): ");
        opcion = leer.nextInt(); leer.nextLine();

        switch (opcion) {
            case 1: ingresarNotas(); 
            break;
            case 2: verEstadisticas(); 
            break;
            case 3: verPorRubro(); 
            break;
            case 4: editarNotas(); 
            break;
            case 5: eliminarNotas(); 
            break;
            case 6 : cargaNotas();
            break;
            case 7: System.out.println("Saliendo..."); logIn();
                break;
            default: System.out.println("Opcion invalida.");
        }
    } while (opcion < 7);
}
    
    //metodo para gestionar las notas
    public static void ingresarNotas() {
    System.out.print("Ingrese el curso: ");
    String curso = leer.nextLine();

    List<String> estudiantes = servicio.estudiantesAsignados(curso);
    if (estudiantes.isEmpty()) {
        System.out.println("No hay estudiantes asignados al curso.");
        return;
    }

    for (String estudiante : estudiantes) {
        System.out.println("Ingresando notas para " + estudiante + ":");
        System.out.print("Trabajo en clase (25%): ");
        double cotidiano = leer.nextDouble();
        System.out.print("Tareas (25%): "); 
        double tareas = leer.nextDouble();
        System.out.print("Pruebas (25%): "); 
        double pruebas = leer.nextDouble();
        System.out.print("Proyecto (25%): "); 
        double proyecto = leer.nextDouble();
        leer.nextLine();

        String linea = curso + " | " + estudiante + " | " + cotidiano + " | " + tareas + " | " + pruebas + " | " + proyecto;
        servicio.escribirArchivo("notas.txt", linea);
    }
}
    
    
    //metodo para mostrar las estadisticas de los cursos
    public static void verEstadisticas() {
    System.out.print("Curso a consultar: ");
    String curso = leer.nextLine();

    List<String[]> registros = new ArrayList<>();
    try (BufferedReader lector = new BufferedReader(new FileReader("notas.txt"))) {
        String linea;
        while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(" \\| ");
            if (partes[0].equalsIgnoreCase(curso)) {
                registros.add(partes);
            }
        }
    } catch (IOException e) {
        System.out.println("Error leyendo notas.");
        return;
    }

    if (registros.isEmpty()) {
        System.out.println("No hay notas registradas para este curso.");
        return;
    }

    double mayor = -1, menor = 101, total = 0;
    String estMayor = "", estMenor = "";
    for (String[] reg : registros) {
        double promedio = servicio.calcularPromedio(reg);
        total += promedio;
        if (promedio > mayor) {
            mayor = promedio;
            estMayor = reg[1];
        }
        if (promedio < menor) {
            menor = promedio;
            estMenor = reg[1];
        }
    }

    System.out.println("Nota mas alta: " + mayor + " (" + estMayor + ")");
    System.out.println("Nota mas baja: " + menor + " (" + estMenor + ")");
    System.out.printf("Promedio general del grupo: %.2f\n", total / registros.size());
}

    //metodo para modificar las notas de los estudiantes
    public static void editarNotas() {
    System.out.print("Curso: ");
    String curso = leer.nextLine();
    System.out.print("Estudiante: ");
    String estudiante = leer.nextLine();

    List<String> lineas = servicio.leerArchivoALista("notas.txt");
    boolean encontrado = false;

    for (int i = 0; i < lineas.size(); i++) {
        String[] partes = lineas.get(i).split(" \\| ");
        if (partes.length == 6 && partes[0].equalsIgnoreCase(curso) && partes[1].equalsIgnoreCase(estudiante)) {
            System.out.println("Notas actuales:");
            System.out.println("Cotidiano: " + partes[2]);
            System.out.println("Tareas: " + partes[3]);
            System.out.println("Pruebas: " + partes[4]);
            System.out.println("Proyecto: " + partes[5]);

            System.out.print("Nuevo Cotidiano: "); double cotidiano = leer.nextDouble();
            System.out.print("Nuevo Tareas: "); double tareas = leer.nextDouble();
            System.out.print("Nuevo Pruebas: "); double pruebas = leer.nextDouble();
            System.out.print("Nuevo Proyecto: "); double proyecto = leer.nextDouble();
            leer.nextLine();

            String nuevaLinea = curso + " | " + estudiante + " | " + cotidiano + " | " + tareas + " | " + pruebas + " | " + proyecto;
            lineas.set(i, nuevaLinea);
            servicio.sobrescribirArchivo("notas.txt", lineas);
            System.out.println("Notas actualizadas correctamente.");
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        System.out.println("No se encontró ese estudiante en el curso.");
    }
}

    //metodo para eliminar las notas
    public static void eliminarNotas() {
    System.out.print("Curso: ");
    String curso = leer.nextLine();
    System.out.print("Estudiante: ");
    String estudiante = leer.nextLine();

    List<String> lineas = servicio.leerArchivoALista("notas.txt");
    boolean eliminado = false;

    
    //Iterador para recorrer y eliminar de forma segura
    Iterator<String> it = lineas.iterator();
    while (it.hasNext()) {
        String linea = it.next();
        String[] partes = linea.split(" \\| ");
        if (partes.length == 6 && partes[0].equalsIgnoreCase(curso) && partes[1].equalsIgnoreCase(estudiante)) {
            it.remove();
            eliminado = true;
            break;
        }
    }

    if (eliminado) {
        servicio.sobrescribirArchivo("notas.txt", lineas);
        System.out.println("Notas eliminadas correctamente.");
    } else {
        System.out.println("No se encontro esa nota.");
    }
}

    //metodo para mostrar las notas por rubro
    public static void verPorRubro() {
    System.out.print("Curso: ");
    String curso = leer.nextLine();

    System.out.println("Seleccione el rubro:");
    System.out.println("1. Trabajo en clase");
    System.out.println("2. Tareas");
    System.out.println("3. Pruebas");
    System.out.println("4. Proyecto");
    int opcion = leer.nextInt(); leer.nextLine();

    int indice = opcion + 1; 
    String[] rubros = {"cotidiano", "Tareas", "Pruebas", "Proyecto"};

    try (BufferedReader lector = new BufferedReader(new FileReader("notas.txt"))) {
        String linea;
        System.out.println("Estudiante | " + rubros[opcion - 1]);
        while ((linea = lector.readLine()) != null) {
            String[] partes = linea.split(" \\| ");
            if (partes[0].equalsIgnoreCase(curso)) {
                System.out.println(partes[1] + " | " + partes[indice]);
            }
        }
    } catch (IOException e) {
        System.out.println("Error leyendo archivo.");
    }
}
    
//metodo para cargar los datos en el archivo notas
   public static void cargaNotas(){
    System.out.println("Cargando datos...");
    servicio.sobrescribirArchivo("notas.txt", new ArrayList<>()); 
    servicio.escribirArchivo("notas.txt", "Matematicas | Juan Perez | 25.00 | 20.00 | 15.00 | 10.00");
    servicio.escribirArchivo("notas.txt", "Matematicas | Maria Gomez | 25.00 | 05.00 | 25.00 | 25.00");
    servicio.escribirArchivo("notas.txt", "Estudios Sociales | Carlos Ruiz | 20.00 | 15.00 | 05.00 | 25.00");
    servicio.escribirArchivo("notas.txt", "Estudios Sociales | Ana Torres | 22.00 | 20.00 | 15.00 | 08.00");
    servicio.escribirArchivo("notas.txt", "Ingles | Laura Diaz | 14.00 | 15.00 | 16.00 | 25.00");
    servicio.escribirArchivo("notas.txt", "Ingles | Kendall Gomez | 25.00 | 10.00 | 15.00 | 20.00");
    servicio.escribirArchivo("notas.txt", "Espanol | Ricardo Munos | 04.00 | 25.00 | 25.00 | 25.00");
    servicio.escribirArchivo("notas.txt", "Espanol | Alex Aragon | 16.00 | 12.00 | 14.00 | 18.00");
    servicio.escribirArchivo("notas.txt", "Informatica | Lorena Munguia | 15.00 | 25.00 | 12.00 | 19.00");
    servicio.escribirArchivo("notas.txt", "Informatica | Pablo Murillo | 25.00 | 20.00 | 18.00 | 17.00");
    servicio.escribirArchivo("notas.txt", "Ciencias | Juan Perez | 20.00 | 20.00 | 20.00 | 20.00");
    servicio.escribirArchivo("notas.txt", "Ciencias | Maria Gomez | 15.00 | 15.00 | 15.00 | 15.00");
    servicio.escribirArchivo("notas.txt", "Religion | Carlos Ruiz | 00.00 | 00.00 | 00.00 | 00.00");
    servicio.escribirArchivo("notas.txt", "Religion | Ana Torres | 25.00 | 09.00 | 25.00 | 25.00");
    servicio.escribirArchivo("notas.txt", "Orientacion| Laura Diaz | 24.00 | 23.00 | 22.00 | 21.00");
    servicio.escribirArchivo("notas.txt", "Orientacion | Kendall Gomez | 14.00 | 08.00 | 25.00 | 09.00");
    servicio.escribirArchivo("notas.txt", "Musica | Ricardo Munos | 23.00 | 20.00 | 15.00 | 15.00");
    servicio.escribirArchivo("notas.txt", "Musica | Alex Aragon | 25.00 | 25.00 | 25.00 | 25.00hvghgvh");
    
    }
   
    //metodo principal, inicializado con el logIn
    public static void main(String[] args) {
      
logIn ();
      
    }
}
        
    
