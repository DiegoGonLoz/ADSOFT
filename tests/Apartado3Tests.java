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

        if(!sistema.proponerProyecto(proyectos.get(0))){
            throw new RuntimeException("Error de proponerProyecto");
        }
        if(!sistema.proponerProyecto(proyectos.get(1))){
            throw new RuntimeException("Error de proponerProyecto");
        }

        mapaApoyos = sistema.obtenerMapaProyectoApoyos();
        System.out.println(mapaApoyos);

        System.out.println("\n");

        Map<ProyectoParticipativo, Set<Ciudadano>> mapaCiudadanos = sistema.obtenerMapaProyectoCiudadanos();
        System.out.println(mapaCiudadanos);

    }

    private static void testApoyarProyectosNormales(List<Proponente> proponentes) throws Exception {
        Sistema sistema = Sistema.getInstance();
        Ciudadano ciudadano1 = (Ciudadano) proponentes.get(0);
        Ciudadano ciudadano2 = (Ciudadano) proponentes.get(1);
        Asociacion asociacion = (Asociacion) proponentes.get(3);


        ProyectoParticipativo proyecto = new ProyectoParticipativo("Proyecto Test", "Descripción test", ciudadano1);
        sistema.proponerProyecto(proyecto);


        ciudadano2.apoyar(proyecto);
        asociacion.apoyar(proyecto);

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
            ciudadano1.apoyar(proyecto1); // Debería lanzar excepción
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
            ciudadano1.apoyar(proyecto2); // Debería lanzar excepción
            System.out.println("ERROR: No se lanzó la excepción proyectoMasDe60Dias");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar proyecto antiguo: " + e.getClass().getSimpleName());
        }

        try {
            // Proponente es miembro de una asociación que ya apoya el proyecto
            Asociacion asociacion = new Asociacion("Asociación Test", "pass", ciudadano1);
            sistema.addProponente(ciudadano1);
            sistema.addProponente(ciudadano2);
            asociacion.inscribir(ciudadano2); // ciudadano2 es miembro de la asociación

            ProyectoParticipativo proyecto3 = new ProyectoParticipativo("Proyecto Asociación", "Descripción", ciudadano1);
            sistema.proponerProyecto(proyecto3);
            asociacion.apoyar(proyecto3); // Primero la asociación apoya
            ciudadano2.apoyar(proyecto3); // Después ciudadano2 (miembro) intenta apoyar - debería lanzar excepción

            System.out.println("ERROR: No se lanzó la excepción enteCiudadanoEsMiembro");
        } catch (Exception e) {
            System.out.println("Excepción correcta al apoyar siendo miembro: " + e.getClass().getSimpleName());
        }
    }

    private static void testValidacionesProyectoFundacion(List<Proponente> proponentes) {
        Fundacion fundacion = (Fundacion) proponentes.get(2);

        try {
            // Presupuesto inválido (<= 0)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 0, 50);
            System.out.println("ERROR: No se lanzó la excepción presupuestoMenorIgualCero");
        } catch (Exception e) {
            System.out.println("Excepción correcta por presupuesto inválido: " + e.getClass().getSimpleName());
        }

        try {
            // Porcentaje inválido (<1)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 0);
            System.out.println("ERROR: No se lanzó la excepción porcentajeInvalido");
        } catch (Exception e) {
            System.out.println("Excepción correcta por porcentaje <1: " + e.getClass().getSimpleName());
        }

        try {
            // Porcentaje inválido (>100)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 101);
            System.out.println("ERROR: No se lanzó la excepción porcentajeInvalido");
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

