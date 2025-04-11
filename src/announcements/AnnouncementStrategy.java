package announcements;

/**
 * Enumeración del tipo de estrategia
 *
 * @author Diego Gonzalez
 */
public enum AnnouncementStrategy {
    /**Todos los mensajes*/ ALL_MESSAGES,
    /**Uno de cada n*/ ONE_IN_N_MESSAGES,
    /**Cuando de alcanzan n apoyos*/ WHEN_N_SUPPORTS_ACHIEVED
}
