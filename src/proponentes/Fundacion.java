package proponentes;

import announcements.*;
import myExceptions.errorAnadiendoFundacionExistente;
import myExceptions.formatoCifIncorrecto;
import proyectos.ProyectoFundacion;

import sistemas.Sistema;

import java.util.*;

public class Fundacion extends Proponente implements FollowedEntity {
    private final String CIF;
    private final Set<FollowerManager> followers = new HashSet<FollowerManager>();

    public Fundacion(String name, String contrasena, String cif) throws NullPointerException, formatoCifIncorrecto {
        super(name, contrasena);
        if(this.validarCIF(cif)){
            this.CIF = cif;
        } else {
            throw new formatoCifIncorrecto("Error en el constructor Fundacion: ");
        }
    }

    private boolean validarCIF(String cif) {
        if (cif == null || cif.isEmpty()) {
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

        for (int i = 0; i < numeroStr.length(); i++) {
            int digito = Character.getNumericValue(numeroStr.charAt(i));

            if ((i + 1) % 2 == 0) {
                sumaPares += digito;
            } else {
                int doble = digito * 2;
                sumaImpares += (doble > 9) ? (doble - 9) : doble;
            }
        }

        int sumaTotal = sumaPares + sumaImpares;

        int digitoCalculado = (10 - (sumaTotal % 10)) % 10;

        if (Character.isLetter(digitoControl)) {
            String letrasControl = "JABCDEFGHI";
            char letraCalculada = letrasControl.charAt(digitoCalculado);
            return digitoControl == letraCalculada;
        } else {
            int digitoControlNum = Character.getNumericValue(digitoControl);
            return digitoCalculado == digitoControlNum;
        }
    }

    public void proponer(ProyectoFundacion proyecto) {
        Sistema.getInstance().proponerProyecto(proyecto);
    }

    @Override
    public String toString() {
        return this.nombre + " CIF (" +this.CIF + ") <fundacion>";
    }

    public List<Ciudadano> todosLosCiudadanos() {
        return null;
    }

    @Override
    public errorAnadiendoFundacionExistente repetido() {
        return new errorAnadiendoFundacionExistente("Fundacion " +this.getNombre()+" con cif "+this.CIF+" ya existente");
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(this.CIF);
    }

    @Override
    public boolean follow(Follower f) {
        return followers.add(new FollowerManagerAllMessages(f));
    }

    @Override
    public boolean unfollow(Follower f) {
        return followers.remove(f);
    }

    @Override
    public void announce(Announcement t) {
        for(FollowerManager follower : followers){
            follower.announce(t);
        }
    }

    @Override
    public boolean follow(Follower f, AnnouncementStrategy ns) {
        return switch (ns) {
            case AnnouncementStrategy.ONE_IN_N_MESSAGES -> followers.add(new FollowerManagerFrecuency(f));
            default -> followers.add(new FollowerManagerAllMessages(f));
        };
    }

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
