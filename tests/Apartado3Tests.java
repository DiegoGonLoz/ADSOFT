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

            System.out.println("\n=== TEST 1: Funciones de mapa ===");
            testFuncionesMapa(proponentes);

            System.out.println("\n=== TEST 2: Apoyar proyectos - Casos normales ===\"");
            testApoyarProyectosNormales(proponentes);

            System.out.println("\n=== TEST 3: Apoyar proyectos - Excepciones ===");
            testApoyarProyectosExcepciones(proponentes);

            System.out.println("\n=== TEST 4: ProyectoFundacion - Validaciones ===");
            testValidacionesProyectoFundacion(proponentes);


        } catch (Exception e) {
            System.err.println("Error durante las pruebas: " + e);
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

        mapaApoyos = sistema.obtenerMapaProyectoApoyos();

        System.out.println("Mapa Proyecto-NumMiembros(Antes):");
        System.out.println(mapaApoyos);

        ((EnteCiudadano)proponentes.get(1)).apoyar(proyectos.get(1));
        ((EnteCiudadano)proponentes.get(2)).apoyar(proyectos.get(1));

        System.out.println("Mapa Proyecto-NumMiembros(Después):");
        System.out.println(mapaApoyos);


        System.out.println("\nMapa Proyecto-Ciudadanos:");

        Map<ProyectoParticipativo, Set<Ciudadano>> mapaCiudadanos = sistema.obtenerMapaProyectoCiudadanos();
        System.out.println(mapaCiudadanos);

    }

    private static void testApoyarProyectosNormales(List<Proponente> proponentes) throws Exception {
        Sistema sistema = Sistema.getInstance();
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);
        Asociacion asociacion = (Asociacion) proponentes.get(3);


        ProyectoCiudadano proyecto = new ProyectoCiudadano("Proyecto Test", "Descripción test", ciudadano1);
        sistema.proponerProyecto(proyecto);


        ciudadano2.apoyar(proyecto);
        asociacion.apoyar(proyecto);

        System.out.println("Apoyos después de añadir ciudadano2 y asociación: " + proyecto.obtenerApoyos());
        System.out.println("Ciudadanos asociados: " + proyecto.todosLosCiudadanos());
    }

    private static void testApoyarProyectosExcepciones(List<Proponente> proponentes) {
        Sistema sistema = Sistema.getInstance();
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);

        try {
            // Proyecto propuesto por sí mismo
            ProyectoCiudadano proyecto1 = new ProyectoCiudadano("Proyecto Autoapoyo", "Descripción", ciudadano1);
            sistema.proponerProyecto(proyecto1);
            ciudadano1.apoyar(proyecto1); // Debería lanzar excepción
            System.out.println("ERROR: No se lanzó la excepción ProyectoPropuestoPorSiMismo");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar proyecto propio: " + e.getClass().getSimpleName());
        }

        try {
            // Proyecto con más de 60 días
            ProyectoCiudadano proyecto2 = new ProyectoCiudadano("Proyecto Antiguo", "Descripción", ciudadano1) {
                @Override
                public LocalDate getFecha() {
                    return LocalDate.now().minusDays(61); // Simular proyecto antiguo
                }
            };
            sistema.proponerProyecto(proyecto2);
            ciudadano1.apoyar(proyecto2); // Debería lanzar excepción
            System.out.println("ERROR: No se lanzó la excepción ProyectoMasDe60Dias");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar proyecto antiguo: " + e.getClass().getSimpleName());
        }

        try {
            // Proponente es miembro de una asociación que ya apoya el proyecto
            Asociacion asociacion = new Asociacion("Asociación Test", "pass", ciudadano1);
            sistema.addProponente(ciudadano1);
            sistema.addProponente(ciudadano2);
            asociacion.inscribir(ciudadano2); // ciudadano2 es miembro de la asociación

            ProyectoCiudadano proyecto3 = new ProyectoCiudadano("Proyecto Asociación", "Descripción", ciudadano1);
            sistema.proponerProyecto(proyecto3);
            asociacion.apoyar(proyecto3); // Primero la asociación apoya
            ciudadano2.apoyar(proyecto3); // Después ciudadano2 (miembro) intenta apoyar - debería lanzar excepción

            System.out.println("ERROR: No se lanzó la excepción EnteCiudadanoEsMiembro");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar siendo miembro: " + e.getClass().getSimpleName());
        }
    }

    private static void testValidacionesProyectoFundacion(List<Proponente> proponentes) {
        Fundacion fundacion = (Fundacion) proponentes.get(5);

        try {
            // Presupuesto inválido (<= 0)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 0, 50);
            System.out.println("ERROR: No se lanzó la excepción PresupuestoMenorIgualCero");
        } catch (Exception e) {
            System.out.println("Excepción correcta por presupuesto inválido: " + e.getClass().getSimpleName());
        }

        try {
            // Porcentaje inválido (<1)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 0);
            System.out.println("ERROR: No se lanzó la excepción PorcentajeInvalido");
        } catch (Exception e) {
            System.out.println("Excepción correcta por porcentaje <1: " + e.getClass().getSimpleName());
        }

        try {
            // Porcentaje inválido (>100)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 101);
            System.out.println("ERROR: No se lanzó la excepción PorcentajeInvalido");
        } catch (Exception e) {
            System.out.println("Excepción correcta por porcentaje >100: " + e.getClass().getSimpleName());
        }

        try {
            // Proyecto válido
            ProyectoFundacion proyectoValido = new ProyectoFundacion("Proyecto Válido", "Descripción", fundacion, 1000, 50);
            System.out.println("ProyectoFundacion creado correctamente: " + proyectoValido);
        } catch (Exception e) {
            System.out.println("ERROR: Excepción inesperada al crear proyecto válido: " + e.getClass().getSimpleName());
        }
    }
}

