package announcements;

import proyectos.ProyectoParticipativo;

public class FollowerManagerProjectSupport extends FollowerManager {
    private final ProyectoParticipativo proyecto;
    private boolean avisado = false;

    public FollowerManagerProjectSupport(Follower follower, ProyectoParticipativo proyecto) {
        super(follower);
        throw
        this.proyecto = proyecto;
    }

    @Override
    public void announce(Announcement t) {
        if(!this.avisado && proyecto.obtenerApoyos() > this.umbral){
            follower.receives(t);
            this.avisado = true;
        }
    }

    @Override
    public void setUmbral(int n){
        super.setUmbral(n);
        this.avisado = false;
    }
}
