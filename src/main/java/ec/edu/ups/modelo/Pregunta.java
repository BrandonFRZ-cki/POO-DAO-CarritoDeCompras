package ec.edu.ups.modelo;

public class Pregunta {
    private int id;
    private String pregunta;
    private String respuesta;
    public Pregunta(int id, String pregunta) {
        this.id = id;
        this.pregunta = pregunta;
        respuesta = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
