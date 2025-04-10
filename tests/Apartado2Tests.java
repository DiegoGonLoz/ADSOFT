import myExceptions.porcentajeInvalido;
import myExceptions.presupuestoMenorIgualCero;
import proponentes.Asociacion;
import proponentes.Ciudadano;
import proponentes.Fundacion;
import proponentes.Proponente;
import proyectos.ProyectoFundacion;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;


public class Apartado2Tests {
    public Apartado2Tests() {}

    public static void main(String[] args) {

        try {
            List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();

            System.out.println("=== TEST 1: Creación de proyectos ===");
            testCreacionProyectos(proponentes);

            System.out.println("=== TEST 2: ProyectoParticipativo ===");
            testProyectoParticipativo((Ciudadano) proponentes.getFirst());
            System.out.println();

            System.out.println("=== TEST 3: ProyectoFundacion ===");
            testProyectoFundacion((Fundacion) proponentes.get(5));
            System.out.println();

        } catch (Exception e) {
            System.err.println("Error durante las pruebas: " + e);
        }
    }



    private static void testCreacionProyectos(List<Proponente> proponentes)
            throws porcentajeInvalido, presupuestoMenorIgualCero {

        List<ProyectoParticipativo> proyectos = obtenerProyectosTest(proponentes);

        Sistema sistema = Sistema.getInstance();

        sistema.proponerProyecto(proyectos.get(0));
        sistema.proponerProyecto(proyectos.get(1));

        System.out.println(sistema.proyectosRegistrados());
    }

    public static List<ProyectoParticipativo> obtenerProyectosTest(List<Proponente> proponentes) {
        ProyectoParticipativo proyectoPart = new ProyectoParticipativo(
                "Limpieza del Manzanares",
                "Propuesta para limpiar el manzanares",
                proponentes.get(3)
        );


        ProyectoFundacion proyectoFund = new ProyectoFundacion(
                "Gastemos menos agua",
                "Propuesta para gastar menos agua",
                proponentes.get(5),
                1000000.0,
                80.0
        );
        return List.of(proyectoPart, proyectoFund);
    }

    private static void testProyectoParticipativo(Ciudadano ciudadano) {
        ProyectoParticipativo proyecto = new ProyectoParticipativo(
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

    private static void testProyectoFundacion(Fundacion fundacion) {
        try {
            ProyectoFundacion proyecto1 = new ProyectoFundacion(
                    "Hospital Infantil",
                    "Construcción de hospital especializado",
                    fundacion,
                    1000000.0,
                    90.0
            );

            System.out.println("Proyecto válido:");
            System.out.println(proyecto1);

            try {
                ProyectoFundacion proyecto2 = new ProyectoFundacion(
                        "Proyecto Inválido",
                        "Presupuesto incorrecto",
                        fundacion,
                        -100.0,
                        50.0
                );
            } catch (presupuestoMenorIgualCero e) {
                System.out.println("Error capturado correctamente: " + e);
            }

            try {
                ProyectoFundacion proyecto3 = new ProyectoFundacion(
                        "Proyecto Inválido",
                        "Porcentaje incorrecto",
                        fundacion,
                        10000.0,
                        0.0
                );
            } catch (porcentajeInvalido e) {
                System.out.println("Error capturado correctamente: " + e);
            }

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e);
        }
    }

}

