package proponentes;

import announcements.Announcement;
import myExceptions.errorAnadiendoCiudadanoExistente;
import myExceptions.errorAnadiendoFundacionExistente;
import myExceptions.errorAnadiendoProponente;
import myExceptions.formatoNifIncorrecto;
import proyectos.ProyectoParticipativo;
import sistemas.Sistema;

import java.util.*;

public class Ciudadano extends EnteCiudadano{
    private final String NIF;
    private final Set<Announcement> mensajes = new TreeSet<Announcement>();

    public Ciudadano(String name, String contraseña, String nif) throws NullPointerException, formatoNifIncorrecto {
        super(name, contraseña);
        if(nif == null){
            throw new NullPointerException("Error en el constructor Ciudadano: NIF es null");
        }
        if(!this.validarNIF(nif)){
            throw new formatoNifIncorrecto("Error en el constructor Ciudadano: ");
        }
        this.NIF = nif;

    }

    private boolean validarNIF(String nif) {
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";

        if (!nif.matches("^\\d{8}[A-Z]$")) {
            return false;
        }

        int numero = Integer.parseInt(nif.substring(0, 8));
        char letra = nif.charAt(8);

        return letra == LETRAS.charAt(numero % 23);
    }

    public void proponer(ProyectoParticipativo proyecto){
        if(Sistema.getInstance().proponerProyecto(proyecto)){
            this.apoyar(proyecto);
        }
    }

    public boolean esMiembro(EnteCiudadano enteCiudadano){
        return this.equals(enteCiudadano);
    }

    public int cantidadMiembros(){
        return 1;
    }

    public Set<Ciudadano> todosLosCiudadanos() {
        return Set.of(this);
    }

    @Override
    public errorAnadiendoCiudadanoExistente repetido() {
        return new errorAnadiendoCiudadanoExistente("Ciudadano " +this.getNombre()+" con nif "+this.NIF+" ya existente");
    }

    public void receives(Announcement t) {
        if(t != null){
            mensajes.add(t);
        }
    }

    public void darseDeBaja(Asociacion asociacion){
        if(asociacion.darDeBaja(this)){
            inscrito.remove(asociacion);
        }
    }

    public void darseDeBaja(){
        for(Asociacion asociacion : inscrito){
            this.darseDeBaja(asociacion);
        }
    }

    @Override
    public String toString() {
        return this.nombre + " NIF (" +this.NIF + ") <usuario>";
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(this.NIF);
    }
}
