package proyectos;

import announcements.*;
import proponentes.*;

import java.time.*;
import java.util.*;
import myExceptions.*;

public class ProyectoCiudadano extends ProyectoParticipativo {

    public ProyectoCiudadano(String titulo, String descripcion, EnteCiudadano proponente) {
        super(titulo, descripcion, proponente);
        this.apoyos.add(proponente);
    }
}
