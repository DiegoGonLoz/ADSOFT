package proponentes;

import announcements.FollowedEntity;
import proyectos.ProyectoParticipativo;

import java.util.List;

public class Asociacion extends EnteCiudadano implements FollowedEntity {
    private final Ciudadano representante;
    private List<Ciudadano> miembros;

    public Asociacion(String nombre, String contraseña, Ciudadano representante){
        super(nombre, contraseña);

        if(representante == null){
            throw Exception;
        }

        this.representante = representante;
    }

    public boolean inscribir(Ciudadano ciudadano){
        if(this.esMiembro(ciudadano)){
            return false;
        }

        return miembros.add(ciudadano);
    }

    public boolean darDeBaja(Ciudadano ciudadano){
        return miembros.remove(ciudadano);
    }

    public void proponer(ProyectoParticipativo proyecto){
        - En proponer llamar a apoyar;
    }

    public void incribirse(Asociacion asociacion){

    }

    public boolean esMiembro(Ciudadano ciudadano){
        if(ciudadano == null){
            return false;
        }
        for(EnteCiudadano ente : miembros){
            if(ente.esMiembro(ciudadano)){
                return true;
            }
        }

        return false;
    }

    public int cantidadMiembros(){
        int total = 0;
        for(EnteCiudadano ente : miembros){
            total += ente.cantidadMiembros();
        }

        return total;
    }

    @Override
    public String toString() {
        return
    }

}
