package proponentes;

import announcements.*;
import myExceptions.ErrorAnadiendoFundacionExistente;
import myExceptions.FormatoCifIncorrecto;
import proyectos.ProyectoFundacion;

import sistemas.Sistema;

import java.util.*;

/**
 * Clase que representa una fundación
 *
 * @author Diego Gonzalez
 */
public class Fundacion extends Proponente implements FollowedEntity {
    /**Cif de la fundacion*/
    private final String CIF;
    /**Set de followers*/
    private final Set<FollowerManager> followers = new HashSet<FollowerManager>();

    /**
     * Constructor de la clase Fundacion
     * @param name nombre de la fundacion
     * @param contrasena contraseña de la fundacion
     * @param cif cif de la fundacion
     * @throws NullPointerException parámetros null
     * @throws FormatoCifIncorrecto cif incorrecto
     */
    public Fundacion(String name, String contrasena, String cif) throws NullPointerException, FormatoCifIncorrecto {
        super(name, contrasena);
        if(this.validarCIF(cif)){
            this.CIF = cif;
        } else {
            throw new FormatoCifIncorrecto("Error en el constructor Fundacion: ");
        }
    }

    /**
     * Metodo para validar un cif
     * @param cif cif a validar
     * @return true o false según sea valido o no
     */
    public boolean validarCIF(String cif) {
        if (cif == null || cif.length() != 9) {
            return false;
        }

        if (!cif.matches("^[ABCDEFGHJKLMNPQRSUVW]\\d{7}[0-9A-J]$")) {
            return false;
        }

        char tipo = cif.charAt(0);
        String numeroStr = cif.substring(1, 8);
        char digitoControl = cif.charAt(8);

        int sumaPares = 0;
        int sumaImpares = 0;

        for (int i = 0; i < 7; i++) {
            int digito = Character.getNumericValue(numeroStr.charAt(i));

            if ((i + 1) % 2 == 0) {
                sumaPares += digito;
            }
            else {
                sumaImpares += digito;
            }
        }

        int sumaTotal = sumaPares + sumaImpares;
        int resto = sumaTotal % 10;
        int digitoCalculado = resto == 0 ? 0 : 10 - resto;

        String letrasControl = "JABCDEFGHI";

        if (Character.isLetter(digitoControl)) {
            return digitoControl == letrasControl.charAt(digitoCalculado);
        } else {
            return digitoCalculado == Character.getNumericValue(digitoControl);
        }
    }

    /**
     * Metodo para proponer proyectos
     * @param proyecto proyecto a proponer
     */
    public void proponer(ProyectoFundacion proyecto) {
        Sistema.getInstance().proponerProyecto(proyecto);
    }

    /**
     * Metodo toString
     * @return string con la información de la fundacion
     */
    @Override
    public String toString() {
        return this.nombre + " CIF (" +this.CIF + ") <fundacion>";
    }

    /**
     * Metodo para obtener a todos los ciudadanos
     * @return set de ciudadanos
     */
    public Set<Ciudadano> todosLosCiudadanos() {
        return null;
    }

    /**
     * Metodo repetido
     * @return excepcion error añadiendo fundacion
     */
    @Override
    public ErrorAnadiendoFundacionExistente repetido() {
        return new ErrorAnadiendoFundacionExistente("Fundacion " +this.getNombre()+" con cif "+this.CIF+" ya existente");
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
        if (obj instanceof Fundacion) {
            return this.CIF.equals(((Fundacion)obj).CIF);
        }
        return false;
    }

    /**
     * Metodo hashCode
     * @return hashCOde del objeto
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.CIF);
    }

    /**
     * Metodo follow para fundacion
     * @param f follower
     * @return true o false
     */
    @Override
    public boolean follow(Follower f) {
        return followers.add(new FollowerManagerAllMessages(f));
    }

    /**
     * Metodo unfollow para fundacion
     * @param f follower
     * @return true o false
     */
    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    /**
     * Metodo para anunciar
     * @param t anuncio
     */
    @Override
    public void announce(Announcement t) {
        for(FollowerManager follower : followers){
            follower.announce(t);
        }
    }

    /**
     * Metodo follow según estrategia para fundacion
     * @param f follower
     * @param ns estrategia a seguir
     * @return true o false
     */
    @Override
    public boolean follow(Follower f, AnnouncementStrategy ns) {
        return switch (ns) {
            case AnnouncementStrategy.ONE_IN_N_MESSAGES -> followers.add(new FollowerManagerFrecuency(f));
            default -> followers.add(new FollowerManagerAllMessages(f));
        };
    }

    /**
     * Metodo changeUmbral
     * @param f follower
     * @param umbral nuevo umbral
     * @return true o false
     */
    public boolean changeUmbral(Follower f, int umbral){
        for(FollowerManager follower : followers){
            if(follower.getFollower().equals(f)){
                follower.setUmbral(umbral);
                return true;
            }
        }
        return false;
    }
}
