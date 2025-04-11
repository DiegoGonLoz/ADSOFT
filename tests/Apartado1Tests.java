import myExceptions.*;
import proponentes.*;
import sistemas.Sistema;

import java.util.*;

public class Apartado1Tests {
    private static List<Proponente> proponentes = new ArrayList<Proponente>();
   public static void main (String[] args) {
        try{
            System.out.println("\n\n=== TEST 1: Creación de proponentes ===");
            testCreacionProponentes();

            System.out.println("\n=== TEST 2: Inscripcion en asociaciones ===");
            testInscripcionAsociaciones();

            System.out.println("\n=== TEST 3: Proponentes repetidos ===");
            testProponentesRepetidos();

            System.out.println("\n=== TEST 4: Busqueda de proponentes ===");
            testBusquedaProponentes();
        } catch (Exception e){
            System.out.println("Error durante las pruebas: \n" + e);
            for(StackTraceElement error : e.getStackTrace()){
                System.out.println(error);
            }
        }

        System.out.println("\n=== FIN DE LAS PRUEBAS DEL APARTADO 1===");
    }

    public static List<Proponente> obtenerProponentesTest(){
       if(!proponentes.isEmpty()) return new ArrayList<>(proponentes);

       List<Proponente> nuevosProponentes = new ArrayList<Proponente>();

       nuevosProponentes.add(new Ciudadano("Juan Bravo", "12345", "01234567L"));
       nuevosProponentes.add(new Ciudadano("Ana López", "12345", "01234568C"));
       nuevosProponentes.add(new Ciudadano("Luisa Gómez", "12345", "01234569K"));
       nuevosProponentes.add(new Asociacion("conservemos el manzanares", "12345", (Ciudadano) nuevosProponentes.get(2)));
       nuevosProponentes.add(new Asociacion("amigos de los pajaros", "12345", (Ciudadano) nuevosProponentes.get(2)));
       nuevosProponentes.add(new Fundacion("Fundación Canal", "12345", "A1234567B"));

       ((Ciudadano) nuevosProponentes.get(0)).inscribirse((Asociacion) nuevosProponentes.get(3));
       ((Ciudadano) nuevosProponentes.get(1)).inscribirse((Asociacion) nuevosProponentes.get(4));
       ((Asociacion) nuevosProponentes.get(4)).inscribirse((Asociacion) nuevosProponentes.get(3));

       proponentes = nuevosProponentes;

       return new ArrayList<>(nuevosProponentes);
    }

    public static void testCreacionProponentes() {
        try{
            new Ciudadano(null, "12345", "01234567L");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Ciudadano("Juan Bravo", null, "01234567L");
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Ciudadano("Juan Bravo", "12345", null);
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Ciudadano("Juan Bravo", "12345", "01234567H");
        } catch (FormatoNifIncorrecto e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Asociacion("conservemos el manzanares", "12345", null);
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Fundacion("Fundación Canal", "12345", "A12345678");
        } catch (FormatoCifIncorrecto e) {
            System.out.println("Error capturado correctamente: " + e);
        }
    }

    public static void testInscripcionAsociaciones() {
        ciudadano a asociacion
        asociacion a asociacion
        asociacion no vacia a asociacion
        asociacion con distinto representante a asociacion
        ciudadano ya perteneciente
        darse de baja

    }

    public static void testProponentesRepetidos() {
       for(Proponente proponente : obtenerProponentesTest()){
           Sistema.getInstance().addProponente(proponente);
       }

       try{
           Sistema.getInstance().addProponente((Ciudadano)obtenerProponentesTest().get(0));
       } catch(ErrorAnadiendoCiudadanoExistente e){
           System.out.println("Error capturado correctamente: " + e);
       }
       try{
           Sistema.getInstance().addProponente((Asociacion)obtenerProponentesTest().get(3));
       } catch(ErrorAnadiendoAsociacionExistente e){
           System.out.println("Error capturado correctamente: " + e);
       }
       try{
           Sistema.getInstance().addProponente((Fundacion)obtenerProponentesTest().get(5));
       } catch(ErrorAnadiendoFundacionExistente e){
           System.out.println("Error capturado correctamente: " + e);
       }
    }

    public static void testBusquedaProponentes() {
        if(!obtenerProponentesTest().get(0).equals(Sistema.getInstance().obtenerCiudadano(obtenerProponentesTest().get(0).getNombre())))
            throw new RuntimeException("Error en la busqueda de ciudadanos");
        else
            System.out.println("Ciudadano encontrado correctamente: " + Sistema.getInstance().obtenerCiudadano(obtenerProponentesTest().get(0).getNombre()));

        System.out.println(Sistema.getInstance().todosLosUsuarios());
    }
}
