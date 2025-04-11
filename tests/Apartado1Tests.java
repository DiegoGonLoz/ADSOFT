import myExceptions.*;
import proponentes.*;
import sistemas.Sistema;

import java.util.*;

public class Apartado1Tests {
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
       List<Proponente> proponentes = new ArrayList<Proponente>();

       proponentes.add(new Ciudadano("Juan Bravo", "12345", "01234567L"));
       proponentes.add(new Ciudadano("Ana López", "12345", "01234568C"));
       proponentes.add(new Ciudadano("Luisa Gómez", "12345", "01234569K"));
       proponentes.add(new Asociacion("conservemos el manzanares", "12345", (Ciudadano)proponentes.get(2)));
       proponentes.add(new Asociacion("amigos de los pajaros", "12345", (Ciudadano)proponentes.get(2)));
       proponentes.add(new Fundacion("Fundación Canal", "12345", "A1234567B"));

       ((Ciudadano)proponentes.get(0)).inscribirse((Asociacion)proponentes.get(3));
       ((Ciudadano)proponentes.get(1)).inscribirse((Asociacion)proponentes.get(4));
       ((Asociacion)proponentes.get(4)).inscribirse((Asociacion)proponentes.get(3));

       return proponentes;
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
        } catch (formatoNifIncorrecto e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Asociacion("conservemos el manzanares", "12345", null);
        } catch (NullPointerException e) {
            System.out.println("Error capturado correctamente: " + e);
        }

        try{
            new Fundacion("Fundación Canal", "12345", "A12345678");
        } catch (formatoCifIncorrecto e) {
            System.out.println("Error capturado correctamente: " + e);
        }
    }

    public static void testInscripcionAsociaciones() {
        /*ciudadano a asociacion
        asociacion a asociacion
        asociacion no vacia a asociacion
        asociacion con distinto representante a asociacion
        ciudadano ya perteneciente
        darse de baja*/

    }

    public static void testProponentesRepetidos() {
       for(Proponente proponente : obtenerProponentesTest()){
           Sistema.getInstance().addProponente(proponente);
       }

       try{
           Sistema.getInstance().addProponente((Ciudadano)obtenerProponentesTest().get(0));
       } catch(errorAnadiendoCiudadanoExistente e){
           System.out.println("Error capturado correctamente: " + e);
       }
       try{
           Sistema.getInstance().addProponente((Asociacion)obtenerProponentesTest().get(3));
       } catch(errorAnadiendoAsociacionExistente e){
           System.out.println("Error capturado correctamente: " + e);
       }
       try{
           Sistema.getInstance().addProponente((Fundacion)obtenerProponentesTest().get(5));
       } catch(errorAnadiendoFundacionExistente e){
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
