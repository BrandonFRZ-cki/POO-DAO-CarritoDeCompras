package ec.edu.ups.modelo;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

/**
 * Representa una pregunta de seguridad que puede ser respondida por un usuario.
 * Cada pregunta tiene un identificador único y una respuesta asociada.
 * También permite obtener la versión traducida de la pregunta mediante el sistema de internacionalización.
 *
 * @author Brandon
 * @version 1.0
 */
public class Pregunta {

    /** Identificador único de la pregunta */
    private int id;

    /** Respuesta asociada a la pregunta */
    private String respuesta;

    /**
     * Constructor que inicializa la pregunta con su identificador.
     *
     * @param id Identificador numérico de la pregunta.
     */
    public Pregunta(int id) {
        this.id = id;
        this.respuesta = null;
    }

    /**
     * Obtiene el identificador de la pregunta.
     *
     * @return El ID de la pregunta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la pregunta.
     *
     * @param id Nuevo ID que se desea asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene la respuesta asignada a esta pregunta.
     *
     * @return Respuesta como texto plano.
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Asigna una respuesta a esta pregunta.
     *
     * @param respuesta Texto de respuesta proporcionado por el usuario.
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Obtiene el texto de la pregunta traducido al idioma actual,
     * usando el manejador de internacionalización.
     *
     * @param handler Manejador de mensajes internacionales.
     * @return Texto traducido de la pregunta.
     */
    public String getPregunta(MensajeInternacionalizacionHandler handler) {
        return handler.get("pregunta." + id);
    }

    /**
     * Alias del método {@link #getId()}.
     * Útil en contextos donde se usa el nombre "código" en lugar de "ID".
     *
     * @return Identificador de la pregunta.
     */
    public int getCodigo() {
        return id;
    }
}
