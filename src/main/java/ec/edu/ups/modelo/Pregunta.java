package ec.edu.ups.modelo;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

public class Pregunta {
    private int id;
    private String respuesta;

    public Pregunta(int id) {
        this.id = id;
        this.respuesta = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    // Obtiene la pregunta traducida
    public String getPregunta(MensajeInternacionalizacionHandler handler) {
        return handler.get("pregunta." + id);
    }

    public int getCodigo() {
        return id;
    }
}
