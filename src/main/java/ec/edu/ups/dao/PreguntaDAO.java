package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.List;

public interface PreguntaDAO {
    void crear(Pregunta pregunta);
    void responder(int codigo,String respuesta);
    Pregunta buscarPorCodigo(int codigo);
    List<Pregunta> listar();

}
