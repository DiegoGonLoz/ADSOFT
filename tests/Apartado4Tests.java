import proponentes.*;
import proyectos.*;
import sistemas.Sistema;

import java.util.List;

public class Apartado4Tests {
    public static void main(String[] args) {
        prepararFollowers();

        try{
            System.out.println("\n\n=== TEST 1: Follow ===");
            testFollow();

            System.out.println("\n=== TEST 2: Unfollow ===");
            testUnfollow();

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 4===");
        } catch (Exception e){
            System.out.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }

    public static void prepararFollowers(){
        List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();
        Sistema sistema = Sistema.getInstance();

        proponentes.add(new Ciudadano("Gran Hermano", "12345", "82882837Z"));

        for(Proponente proponente : proponentes){
            sistema.addProponente(proponente);
        }

        List<ProyectoParticipativo> proyectos = Apartado2Tests.obtenerProyectosTest(proponentes);

        ((Fundacion)proponentes.get(5)).follow((EnteCiudadano)proponentes.getFirst());
        proyectos.getFirst().follow((EnteCiudadano)proponentes.getLast());
        new Ciudadano("Winston Smith", "12345", "40208197B").apoyar(proyectos.getFirst());

        proponentes.get(3).proponer(proyectos.getFirst());
        proponentes.get(5).proponer(proyectos.get(1));
    }

    private static void testFollow() {
        int i;

        List<Proponente> proponentes = Sistema.getInstance().todosLosUsuarios();

        for(i=0; i<3; i++){
            System.out.println("Anuncios para "+ proponentes.get(i).getNombre() +": \n" + ((Ciudadano)proponentes.get(i)).todosLosMensajes());
        }

        System.out.println("Anuncios para "+ proponentes.getLast().getNombre() +": \n" + ((Ciudadano)proponentes.getLast()).todosLosMensajes());
    }

    private static void testUnfollow() {

    }
}
