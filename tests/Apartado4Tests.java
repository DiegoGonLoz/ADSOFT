import proponentes.*;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.List;

public class Apartado4Tests {
    public static void main(String[] args) {
        try{
            System.out.println("\n\n=== TEST 1: Follow ===");
            testFollow();

            System.out.println("\n=== TEST 2: Unfollow ===");
            /*testUnfollow();*/

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 4===");
        } catch (Exception e){
            System.out.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }

    private static void testFollow() {
        int i;
        List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();
        List<ProyectoParticipativo> proyectos = Apartado2Tests.obtenerProyectosTest(proponentes);
        Sistema sistema = Sistema.getInstance();

        for(Proponente proponente : proponentes){
            sistema.addProponente(proponente);
        }

        proyectos.get(1).follow((EnteCiudadano)proponentes.getFirst());

        for(ProyectoParticipativo proyecto : proyectos){
            sistema.proponerProyecto(proyecto);
        }

        for(i=0; i<3; i++){
            System.out.println(((Ciudadano)proponentes.get(i)).todosLosMensajes());
        }
    }
}
