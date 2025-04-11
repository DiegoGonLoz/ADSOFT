package proponentes;

import announcements.FollowedEntity;
import announcements.Follower;
import myExceptions.ErrorAnadiendoCiudadanoExistente;
import proyectos.ProyectoParticipativo;
import proyectos.ProyectoCiudadano;

import java.util.LinkedList;

public abstract class EnteCiudadano extends Proponente implements Follower {
    protected LinkedList<Asociacion> inscrito = new LinkedList<Asociacion>();
    protected LinkedList<FollowedEntity> seguidos = new LinkedList<FollowedEntity>();

    public EnteCiudadano(String nombre, String contraseña) throws NullPointerException{
        super(nombre, contraseña);
    }

    public abstract void proponer(ProyectoParticipativo proyecto);

    public boolean apoyar(ProyectoParticipativo proyecto){
        return proyecto.sumarApoyo(this);
    }

    public void inscribirse(Asociacion asociacion) throws ErrorAnadiendoCiudadanoExistente {
        if(asociacion.inscribir(this)){
            inscrito.add(asociacion);
            asociacion.follow(this);
        }
    }

    public abstract boolean esMiembro(EnteCiudadano enteCiudadano);

    public abstract int cantidadMiembros();
}
