import myExceptions.PorcentajeInvalido;
import myExceptions.PresupuestoMenorIgualCero;
import proponentes.*;
import proyectos.*;
import sistemas.Sistema;

import java.util.List;


public class Apartado2Tests {
    public Apartado2Tests() {}

    public static void main(String[] args) {

        try {
            List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();

            System.out.println("\n\n=== TEST 1: Creación de proyectos ===");
            testCreacionProyectos(proponentes);

            System.out.println("\n=== TEST 2: ProyectoParticipativo ===");
            testProyectoParticipativo((Ciudadano) proponentes.getFirst());
            System.out.println();

            System.out.println("\n=== TEST 3: ProyectoFundacion ===");
            testProyectoFundacion(proponentes);
            System.out.println();

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 2===");

        } catch (Exception e) {
            System.err.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }

    private static void testCreacionProyectos(List<Proponente> proponentes)
            throws PorcentajeInvalido, PresupuestoMenorIgualCero {

        List<ProyectoParticipativo> proyectos = obtenerProyectosTest(proponentes);

        Sistema sistema = Sistema.getInstance();

        sistema.proponerProyecto(proyectos.get(0));
        sistema.proponerProyecto(proyectos.get(1));

        System.out.println(sistema.proyectosRegistrados());
    }

    public static List<ProyectoParticipativo> obtenerProyectosTest(List<Proponente> proponentes) {
        ProyectoCiudadano proyectoPart = new ProyectoCiudadano(
                "Limpieza del Manzanares",
                "Propuesta para limpiar el manzanares",
                (EnteCiudadano)proponentes.get(3)
        );


        ProyectoFundacion proyectoFund = new ProyectoFundacion(
                "Gastemos menos agua",
                "Propuesta para gastar menos agua",
                (Fundacion)proponentes.get(5),
                1000000.0,
                80.0
        );
        ((EnteCiudadano)proponentes.getFirst()).apoyar(proyectoFund);
        return List.of(proyectoPart, proyectoFund);
    }

    private static void testProyectoParticipativo(Ciudadano ciudadano) {
        ProyectoCiudadano proyecto = new ProyectoCiudadano(
                "Biblioteca Municipal",
                "Nueva biblioteca para el barrio norte",
                ciudadano
        );

        System.out.println("Código del proyecto: " + proyecto.getCodigo());
        System.out.println("Título: " + proyecto.getTitulo());
        System.out.println("Proponente: " + proyecto.getProponente());
        System.out.println("Fecha creación: " + proyecto.getFecha());
        System.out.println("Hora creación: " + proyecto.getHora());
    }

    private static void testProyectoFundacion(List<Proponente> proponentes) {
        Fundacion fundacion = (Fundacion) proponentes.get(5);

        try {
            // Presupuesto inválido (<= 0)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 0, 50);
            throw new RuntimeException("Error no detectado al crear un proyecto con presupuesto inválido");
        } catch (PresupuestoMenorIgualCero e) {
            System.out.println("Excepción correcta por presupuesto inválido: \n" + e);
        }

        try {
            // Porcentaje inválido (<1)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 0);
            throw new RuntimeException("Error no detectado al crear un proyecto con porcentaje inválido");
        } catch (PorcentajeInvalido e) {
            System.out.println("Excepción correcta por porcentaje <1: \n" + e);
        }

        try {
            // Porcentaje inválido (>100)
            new ProyectoFundacion("Proyecto Inválido", "Descripción", fundacion, 1000, 101);
            throw new RuntimeException("Error no detectado al crear un proyecto con porcentaje inválido");
        } catch (PorcentajeInvalido e) {
            System.out.println("Excepción correcta por porcentaje >100: \n" + e);
        }

        // Proyecto válido
        ProyectoFundacion proyectoValido = new ProyectoFundacion("Proyecto Válido", "Descripción", fundacion, 1000, 50);
        System.out.println("ProyectoFundacion creado correctamente: \n" + proyectoValido);

    }

}

