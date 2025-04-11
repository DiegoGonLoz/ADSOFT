package proyectos;

import announcements.*;
import proponentes.*;

import java.time.*;
import java.util.*;
import myExceptions.*;

/**
 * Clase que representa a proyecto ciudadano
 *
 * @author Diego Gonzalez
 */
public class ProyectoCiudadano extends ProyectoParticipativo {
    /**
     * Constructor de la clase ProyectoCiudadano
     * @param titulo título del proyecto
     * @param descripcion descripción del proyecto
     * @param proponente proponente del proyecto
     */
    public ProyectoCiudadano(String titulo, String descripcion, EnteCiudadano proponente) {
        super(titulo, descripcion, proponente);
        this.apoyos.add(proponente);
    }
}
