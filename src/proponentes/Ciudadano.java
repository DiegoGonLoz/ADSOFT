package proponentes;

import announcements.Announcement;
import myExceptions.ErrorAnadiendoCiudadanoExistente;
import myExceptions.FormatoNifIncorrecto;
import proyectos.ProyectoCiudadano;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.*;

/**
 * Clase que representa a un ciudadano
 *
 * @author Diego Gonzalez
 */
public class Ciudadano extends EnteCiudadano{
    /**Nif del ciudadano*/
    private final String NIF;
    /**Set de announcements*/
    private final Set<Announcement> mensajes = new TreeSet<Announcement>();

    /**
     * Constructor de Ciudadano
     * @param name nombre
     * @param contraseña contraseña
     * @param nif nif único
     * @throws NullPointerException no objetos null
     * @throws FormatoNifIncorrecto nif incorrecto
     */
    public Ciudadano(String name, String contraseña, String nif) throws NullPointerException, FormatoNifIncorrecto {
        super(name, contraseña);
        if(nif == null){
            throw new NullPointerException("Error en el constructor Ciudadano: \nNIF es null");
        }
        if(!this.validarNIF(nif)){
            throw new FormatoNifIncorrecto("Error en el constructor Ciudadano: \n");
        }
        this.NIF = nif;

    }

    /**
     * Metodo para validar un nif
     * @param nif nif a validar
     * @return true o false
     */
    private boolean validarNIF(String nif) {
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (!nif.matches("^\\d{8}[A-Z]$")) {
            return false;
        }

        int numero = Integer.parseInt(nif.substring(0, 8));
        char letra = nif.charAt(8);

        return letra == LETRAS.charAt(numero % 23);
    }

    /**
     * Metodo para proponer un proyecto
     * @param proyecto proyecto a proponer
     */
    public void proponer(ProyectoParticipativo proyecto){
        if(Sistema.getInstance().proponerProyecto(proyecto)){
            this.apoyar(proyecto);
        }
    }

    /**
     * Metodo para saber si un enteCiudadano es miembro
     * @param enteCiudadano ente a comparar
     * @return true o false
     */
    public boolean esMiembro(EnteCiudadano enteCiudadano){
        return this.equals(enteCiudadano);
    }

    /**
     * Metodo para obtener la cantidad de miembros
     * @return cantidad de miembros
     */
    public int cantidadMiembros(){
        return 1;
    }

    /**
     * Metodo para obtener a todos loc ciudadanos
     * @return set de ciudadanos
     */
    public Set<Ciudadano> todosLosCiudadanos() {
        return Set.of(this);
    }

    /**
     * Metodo toString
     * @return String con la información del ciudadano
     */
    @Override
    public ErrorAnadiendoCiudadanoExistente repetido() {
        return new ErrorAnadiendoCiudadanoExistente("Ciudadano " +this.getNombre()+" con nif "+this.NIF+" ya existente");
    }

    /**
     * Metodo receives
     * @param t anuncio a recibir
     */
    public void receives(Announcement t) {
        if(t != null){
            mensajes.add(t);
        }
    }

    /**
     * Metodo todosLosMensajes
     * @return set con todos los mensajes recibidos
     */
    public Set<Announcement> todosLosMensajes() {
        return new HashSet<>(mensajes);
    }

    /**
     * Metodo para darse de baja de una asociacion
     * @param asociacion a la que darse de baja
     */
    public void darseDeBaja(Asociacion asociacion){
        if(asociacion.darDeBaja(this)){
            inscrito.remove(asociacion);
        }
    }

    /**
     * Metodo para darse de baja de todas las asociaciones
     */
    public void darseDeBaja() {
        Iterator<Asociacion> iterator = inscrito.iterator();
        while (iterator.hasNext()) {
            Asociacion asociacion = iterator.next();
            iterator.remove();
            this.darseDeBaja(asociacion);
        }
    }

    /**
     * Metodo toString
     * @return string con la información del ciudadano
     */
    @Override
    public String toString() {
        return this.nombre + " NIF (" +this.NIF + ") <usuario>";
    }

    /**
     * Metodo equals
     * @param obj objeto a comparar
     * @return true o false
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if (this == obj) {
            return true;
        }
        if (obj instanceof Ciudadano) {
            return this.NIF.equals(((Ciudadano)obj).NIF);
        }
        return false;
    }

    /**
     * Metodo hashCode
     * @return hashCode asignado al ciudadano
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.NIF);
    }
}
