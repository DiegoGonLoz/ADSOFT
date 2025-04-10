import myExceptions.porcentajeInvalido;
import myExceptions.presupuestoMenorIgualCero;
import proponentes.Asociacion;
import proponentes.Ciudadano;
import proponentes.Fundacion;
import proponentes.Proponente;
import proyectos.ProyectoFundacion;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;


public class Apartado3Tests {
    public Apartado3Tests() {}

    public static void main(String[] args) {

        try {
            List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTests();

            System.out.println("\n=== TEST 1: Funciones de mapa ===");
            testFuncionesMapa(proponentes);

            System.out.println("\n=== TEST 2: Apoyar proyectos - Casos normales ===\"");
            testApoyarProyectosNormales(proponentes);




        } catch (Exception e) {
            System.err.println("Error durante las pruebas: " + e);
        }
    }


    private static void testFuncionesMapa(List<Proponente> proponentes) {
        Sistema sistema = Sistema.getInstance();
        SortedMap<ProyectoParticipativo, Integer> mapaApoyos;

        List<ProyectoParticipativo> proyectos = Apartado2Tests.obtenerProyectosTest(proponentes);

        sistema.proponerProyecto(proyectos.get(0));
        sistema.proponerProyecto(proyectos.get(1));

        mapaApoyos = sistema.obtenerMapaProyectoApoyos();
        System.out.println(mapaApoyos);

        System.out.println("\n");

        Map<ProyectoParticipativo, List<Ciudadano>> mapaCiudadanos = sistema.obtenerMapaProyectoCiudadanos();
        System.out.println(mapaCiudadanos);

    }

    private static void testApoyarProyectosNormales(List<Proponente> proponentes) throws Exception {
        Sistema sistema = Sistema.getInstance();
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);
        Asociacion asociacion = (Asociacion) proponentes.get(3);


        ProyectoParticipativo proyecto = new ProyectoParticipativo("Proyecto Test", "Descripción test", ciudadano1);
        sistema.proponerProyecto(proyecto);


        proyecto.apoyar(ciudadano2);
        proyecto.apoyar(asociacion);

        System.out.println("Apoyos después de añadir ciudadano2 y fundación: " + proyecto.obtenerApoyos());
        System.out.println("Ciudadanos asociados: " + proyecto.todosLosCiudadanos());
    }

    private static void testApoyarProyectosExcepciones(List<Proponente> proponentes) {
        Sistema sistema = Sistema.getInstance();
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);

        try {
            // Proyecto propuesto por sí mismo
            ProyectoParticipativo proyecto1 = new ProyectoParticipativo("Proyecto Autoapoyo", "Descripción", ciudadano1);
            sistema.proponerProyecto(proyecto1);
            proyecto1.apoyar(ciudadano1); // Debería lanzar excepción
            System.out.println("ERROR: No se lanzó la excepción proyectoPropuestoPorSiMismo");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar proyecto propio: " + e.getClass().getSimpleName());
        }

        try {
            // Proyecto con más de 60 días
            ProyectoParticipativo proyecto2 = new ProyectoParticipativo("Proyecto Antiguo", "Descripción", ciudadano1) {
                @Override
                public LocalDate getFecha() {
                    return LocalDate.now().minusDays(61); // Simular proyecto antiguo
                }
            };
            sistema.proponerProyecto(proyecto2);
            proyecto2.apoyar(ciudadano2); // Debería lanzar excepción
            System.out.println("ERROR: No se lanzó la excepción proyectoMasDe60Dias");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar proyecto antiguo: " + e.getClass().getSimpleName());
        }

        try {
            // Proponente es miembro de una asociación que ya apoya el proyecto
            Asociacion asociacion = new Asociacion("Asociación Test", "pass", ciudadano1);
            sistema.addCiudadano(ciudadano1);
            sistema.addCiudadano(ciudadano2);
            asociacion.inscribir(ciudadano2); // ciudadano2 es miembro de la asociación

            ProyectoParticipativo proyecto3 = new ProyectoParticipativo("Proyecto Asociación", "Descripción", ciudadano1);
            sistema.proponerProyecto(proyecto3);
            proyecto3.apoyar(asociacion); // Primero la asociación apoya
            proyecto3.apoyar(ciudadano2); // Después ciudadano2 (miembro) intenta apoyar - debería lanzar excepción

            System.out.println("ERROR: No se lanzó la excepción enteCiudadanoEsMiembro");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar siendo miembro: " + e.getClass().getSimpleName());
        }
    }
}

