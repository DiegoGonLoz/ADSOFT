import announcements.AnnouncementStrategy;
import proponentes.*;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.List;

/**
 * Clase encargada de los tests del apartado 5
 */
public class Apartado5Tests {

    /**
     * Metodo main donde se ejecutan los tests
     * @param args vacio
     */
    public static void main(String[] args) {
        try{
            System.out.println("\n\n=== TEST 1: Frecuency follower ===");
            testFrecuencyFollower();

            System.out.println("\n=== TEST 2: N_Support follower ===");
            testNSupportFollower();

            System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 5===");
        } catch (Exception e){
            System.out.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }
    }

    /**
     * Metodo de prueba para notificaciones cada cierta frecuencia
     */
    private static void testFrecuencyFollower(){
        List<Proponente> proponentes = Apartado1Tests.obtenerProponentesTest();
        Sistema sistema = Sistema.getInstance();

        proponentes.add(new Ciudadano("Gran Hermano", "12345", "82882837Z"));

        for(Proponente proponente : proponentes){
            sistema.addProponente(proponente);
        }

        List<ProyectoParticipativo> proyectos = Apartado2Tests.obtenerProyectosTest(proponentes);

        ((Fundacion)proponentes.get(5)).follow((EnteCiudadano)proponentes.getFirst());
        proyectos.getFirst().follow((EnteCiudadano)proponentes.getLast(), AnnouncementStrategy.ONE_IN_N_MESSAGES);
        proyectos.getFirst().changeUmbral((EnteCiudadano)proponentes.getLast(), 2);

        proponentes.get(3).proponer(proyectos.getFirst());
        proponentes.get(5).proponer(proyectos.get(1));

        new Ciudadano("Winston Smith", "12345", "40208197B").apoyar(proyectos.getFirst());
        new Ciudadano("Julia", "12345", "72831371P").apoyar(proyectos.getFirst());

        System.out.println("Anuncios para "+ proponentes.getLast().getNombre() +": \n" + ((Ciudadano)proponentes.getLast()).todosLosMensajes());
    }

    /**
     * Metodo de prueba para recibir una alerta cuando un proyecto alcanza un umbral de apoyos
     */
    private static void testNSupportFollower(){
        Ciudadano ciudadano;

        ProyectoParticipativo proyecto = Sistema.getInstance().obtenerProyecto("Limpieza del Manzanares");
        ciudadano  = new Ciudadano("Goldstein", "12345", "67998981X");
        proyecto.follow(ciudadano, AnnouncementStrategy.WHEN_N_SUPPORTS_ACHIEVED);
        proyecto.changeUmbral(ciudadano, 7);

        Asociacion asociacion =new Asociacion("Los goldensteinstas", "12345", ciudadano);
        new Ciudadano("O'Brien", "12345", "63697785K").inscribirse(asociacion);
        new Ciudadano("Ampleforth", "12345", "73906112M").inscribirse(asociacion);

        asociacion.apoyar(proyecto);

        System.out.println("Anuncios para " + ciudadano.getNombre() +": \n" + ciudadano.todosLosMensajes());
        new Ciudadano("Tom Parsons", "12345", "30193077B").apoyar(proyecto);
        System.out.println("Anuncios para " + ciudadano.getNombre() +" (no debería recibir más mensajes del proyecto): \n" + ciudadano.todosLosMensajes());


        ciudadano = Sistema.getInstance().obtenerCiudadano("Gran Hermano");
        System.out.println("Anuncios para " + ciudadano.getNombre() +" (Sigue recibiendo cada 2 mensajes): \n" + ciudadano.todosLosMensajes());
    }
}
