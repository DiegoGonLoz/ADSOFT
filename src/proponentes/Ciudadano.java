package proponentes;

import announcements.Announcement;
import proyectos.ProyectoParticipativo;

import java.util.LinkedList;

public class Ciudadano extends EnteCiudadano{
    private final String NIF;
    private final LinkedList<Announcement> mensajes = new LinkedList<>();

    public Ciudadano(String name, String contraseña, String nif) {
        super(name, contraseña);
        if(!this.validarNIF(nif)){
            throw Exception;
        }
        this.NIF = nif;

    }

    private boolean validarNIF(String nif) {
        String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE";


        if (nif == null ||  nif.isEmpty()){
            return false;
        }

        if (!nif.matches("^\\d{8}[A-Z]$")) {
            return false;
        }

        int numero = Integer.parseInt(nif.substring(0, 8));
        char letra = nif.charAt(8);

        return letra == LETRAS.charAt(numero % 23);
    }

    public void proponer(ProyectoParticipativo proyecto){
        if(Sistema.getInstance().proponerProyecto(proyecto)){
            proyecto.apoyar(this);
        }
    }

    public void inscribirse(Asociacion asociacion){
        if(asociacion.inscribir(this)){
            inscrito.add(asociacion);
            asociacion.follow(this);
        }
    }

    public boolean esMiembro(EnteCiudadano enteCiudadano){
        return this.equals(enteCiudadano);
    }

    public int cantidadMiembros(){
        return 1;
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
}
