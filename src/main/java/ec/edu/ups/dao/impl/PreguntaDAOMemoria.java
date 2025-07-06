package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {
    private List<Pregunta> preguntas;

    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            crear(new Pregunta(i));
        }
    }

    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        for (Pregunta p : preguntas) {
            if (p.getId() == codigo) {
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
        if (p != null) {
            p.setRespuesta(respuesta);
        }
    }
}
