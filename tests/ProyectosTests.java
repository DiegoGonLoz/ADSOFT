/*import myExceptions.PorcentajeInvalido;
import myExceptions.PresupuestoMenorIgualCero;
import proponentes.Ciudadano;
import proponentes.Fundacion;
import proyectos.ProyectoFundacion;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;


public class ProyectosTests {
    public ProyectosTests() {}

    public static void main(String[] args) {
        Sistema sistema = Sistema.getInstance();

        try {
            Ciudadano ciudadano1 = new Ciudadano("Juan Pérez", "contraseña123", "12345678Z");
            Fundacion fundacion1 = new Fundacion("Fundación Solidaria", "fundacion123", "A12345674");

            System.out.println("=== TEST 1: Creación de proyectos ===");
            testCreacionProyectos(ciudadano1, fundacion1);


            System.out.println("\n=== TEST 2: Funciones de mapa ===");
            testFuncionesMapa(ciudadano1, fundacion1);

            System.out.println("=== TEST 3: ProyectoParticipativo ===");
            testProyectoParticipativo(ciudadano1);
            System.out.println();

            System.out.println("=== TEST 4: ProyectoFundacion ===");
            testProyectoFundacion(fundacion1);
            System.out.println();

        } catch (Exception e) {
            System.err.println("Error durante las pruebas: " + e);
        }
    }


    private static void testCreacionProyectos(Ciudadano ciudadano, Fundacion fundacion)
            throws PorcentajeInvalido, PresupuestoMenorIgualCero {

        ProyectoParticipativo proyectoPart = new ProyectoParticipativo(
                "Parque Central",
                "Creación de un parque en el centro de la ciudad",
                ciudadano
        );


        ProyectoFundacion proyectoFund = new ProyectoFundacion(
                "Escuela Rural",
                "Construcción de una escuela en zona rural",
                fundacion,
                50000.0,
                80.0
        );

        Sistema sistema = Sistema.getInstance();

        sistema.proponerProyecto(proyectoPart);
        sistema.proponerProyecto(proyectoFund);

        System.out.println(sistema.proyectosRegistrados());
    }


    private static void testFuncionesMapa(Ciudadano ciudadano, Fundacion fundacion) {
        Sistema sistema = Sistema.getInstance();
        SortedMap<Integer, ProyectoParticipativo> mapaApoyos;

        ProyectoParticipativo proyectoPart = new ProyectoParticipativo(
                "Parque Central",
                "Creación de un parque en el centro de la ciudad",
                ciudadano
        );


        ProyectoFundacion proyectoFund = new ProyectoFundacion(
                "Escuela Rural",
                "Construcción de una escuela en zona rural",
                fundacion,
                50000.0,
                80.0
        );

        sistema.proponerProyecto(proyectoPart);
        sistema.proponerProyecto(proyectoFund);

        mapaApoyos = sistema.obtenerMapaProyectoApoyos();
        System.out.println(mapaApoyos);

        System.out.println("\n");

        Map<ProyectoParticipativo, Set<Ciudadano>> mapaCiudadanos = sistema.obtenerMapaProyectoCiudadanos();
        System.out.println(mapaCiudadanos);

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
            } catch (PresupuestoMenorIgualCero e) {
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
            } catch (PorcentajeInvalido e) {
                System.out.println("Error capturado correctamente: " + e);
            }

        } catch (Exception e) {
            System.err.println("Error inesperado: " + e);
        }
    }

}
*/