import myExceptions.*;
import proponentes.*;
import proyectos.ProyectoFundacion;
import proyectos.ProyectoCiudadano;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


public class Apartado3Tests {
    public Apartado3Tests() {}

    public static void main(String[] args) {

        try {
            List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();

            System.out.println("\n=== TEST 1: Apoyar proyectos - Casos normales ===\"");
            testApoyarProyectosNormales(proponentes);

            System.out.println("\n=== TEST 2: Apoyar proyectos - Excepciones ===");
            testApoyarProyectosExcepciones(proponentes);

            System.out.println("\n=== TEST 3: Funciones de mapa ===");
            testFuncionesMapa(proponentes);

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 3===");

        } catch (Exception e) {
            System.err.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }


    private static void testFuncionesMapa(List<Proponente> proponentes) {
        Sistema sistema = Sistema.getInstance();
        SortedMap<ProyectoParticipativo, Integer> mapaApoyos;

        List<ProyectoParticipativo> proyectos = Apartado2Tests.obtenerProyectosTest(proponentes);

        if(!sistema.proponerProyecto(proyectos.get(1))){
            throw new RuntimeException("Error de proponerProyecto");
        }
        if(!sistema.proponerProyecto(proyectos.get(0))){
            throw new RuntimeException("Error de proponerProyecto");
        }

        System.out.println("\nMapa Proyecto-NumMiembros(Antes):");
        System.out.println(sistema.obtenerMapaProyectoApoyos());

        ((EnteCiudadano)proponentes.get(1)).apoyar(proyectos.get(1));
        ((EnteCiudadano)proponentes.get(2)).apoyar(proyectos.get(1));

        System.out.println("\nMapa Proyecto-NumMiembros(Después):");
        System.out.println(sistema.obtenerMapaProyectoApoyos());


        System.out.println("\nMapa Proyecto-Ciudadanos:");

        Map<ProyectoParticipativo, Set<Ciudadano>> mapaCiudadanos = sistema.obtenerMapaProyectoParticipativos();
        System.out.println(mapaCiudadanos);

    }

    private static void testApoyarProyectosNormales(List<Proponente> proponentes){
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);
        Asociacion asociacion = (Asociacion) proponentes.get(3);

        ProyectoCiudadano proyecto = new ProyectoCiudadano("Proyecto Test", "Descripción test", ciudadano1);

        ciudadano2.apoyar(proyecto);
        asociacion.apoyar(proyecto);

        System.out.println("Apoyos después de añadir ciudadano2 y asociación: \n" + proyecto.obtenerApoyos());
        System.out.println("Ciudadanos asociados: \n" + proyecto.todosLosCiudadanos());
    }

    private static void testApoyarProyectosExcepciones(List<Proponente> proponentes) {
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);

        try {
            // Proyecto propuesto por sí mismo
            ProyectoCiudadano proyecto1 = new ProyectoCiudadano("Proyecto Autoapoyo", "Descripción", ciudadano1);
            ciudadano1.apoyar(proyecto1);
            throw new RuntimeException("Error no detectado al apoyar proyecto propio");
        } catch (ErrorApoyandoProyecto e) {
            System.out.println("Excepción correcta al apoyar proyecto propio: \n" + e);
        }

        try {
            // Proyecto con más de 60 días
            ProyectoCiudadano proyecto2 = new ProyectoCiudadano("Proyecto Antiguo", "Descripción", ciudadano1) {
                @Override
                public LocalDate getFecha() {
                    return LocalDate.now().minusDays(61); // Simular proyecto antiguo
                }
            };
            ciudadano1.apoyar(proyecto2);
            throw new RuntimeException("Error no detectado al apoyar proyecto antiguo");
        } catch (ErrorApoyandoProyecto e) {
            System.out.println("Excepción correcta al apoyar proyecto antiguo: \n" + e);
        }

        try {
            // Proponente es miembro de una asociación que ya apoya el proyecto
            Asociacion asociacion = new Asociacion("Asociación Test", "pass", ciudadano1);
            asociacion.inscribir(ciudadano2); // ciudadano2 es miembro de la asociación

            ProyectoCiudadano proyecto3 = new ProyectoCiudadano("Proyecto Asociación", "Descripción", ciudadano1);
            asociacion.apoyar(proyecto3); // Primero la asociación apoya
            ciudadano2.apoyar(proyecto3); // Después ciudadano2 (miembro) intenta apoyar - debería lanzar excepción

            throw new RuntimeException("Error no detectado cuando un miembro existente intenta volver a apoyar");
        } catch (ErrorApoyandoProyecto e) {
            System.out.println("Excepción correcta al apoyar siendo miembro: \n" + e);
        }
    }
}

