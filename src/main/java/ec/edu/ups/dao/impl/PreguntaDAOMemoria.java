package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {
    private List<Pregunta> preguntas;
    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<Pregunta>();

        crear(1, "¿Cuál es el segundo nombre de tu madre?");
        crear(2, "¿Cómo se llamaba tu primer maestro o maestra?");
        crear(3, "¿Cuál fue tu caricatura favorita en la infancia?");
        crear(4, "¿Cuál es el nombre de tu mejor amigo/a de la infancia?");
        crear(5, "¿Qué nombre tenía tu primer mascota?");
        crear(6, "¿En qué ciudad nació tu madre?");
        crear(7, "¿Cómo se llama la calle donde creciste?");
        crear(8, "¿Qué libro te marcó cuando eras niño/a?");
        crear(9, "¿Cuál es el apodo que solo tu familia te dice?");
        crear(10, "¿Cuál fue tu juguete favorito de la infancia?");
    }
    @Override
    public void crear(int codigo, String pregunta) {
        preguntas.add(new Pregunta(codigo, pregunta));
    }

    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        for (Pregunta p : preguntas) {
            if(p.getId() == codigo){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Pregunta> listar() {
        return preguntas;
    }

    @Override
    public void responder(int codigo, String respuesta) {
        Pregunta p = buscarPorCodigo(codigo);
        p.setRespuesta(respuesta);
    }
}
