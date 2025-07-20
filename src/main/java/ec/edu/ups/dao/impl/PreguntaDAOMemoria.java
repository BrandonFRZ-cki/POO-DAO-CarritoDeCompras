package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de {@link PreguntaDAO} que utiliza una lista en memoria
 * para almacenar las preguntas de seguridad.
 *
 * <p>Este DAO simula el almacenamiento sin necesidad de archivos ni bases de datos.
 * Crea automáticamente las 10 preguntas predefinidas con sus respectivos códigos del 1 al 10.</p>
 *
 * @author Brandon
 * @version 1.0
 */
public class PreguntaDAOMemoria implements PreguntaDAO {

    private List<Pregunta> preguntas;

    /**
     * Constructor que inicializa la lista de preguntas con 10 objetos {@link Pregunta}
     * con códigos del 1 al 10.
     */
    public PreguntaDAOMemoria() {
        preguntas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            crear(new Pregunta(i));
        }
    }

    /**
     * Agrega una pregunta a la lista en memoria.
     *
     * @param pregunta Objeto {@link Pregunta} que se va a agregar.
     */
    public void crear(Pregunta pregunta) {
        preguntas.add(pregunta);
    }

    /**
     * Busca una pregunta por su código.
     *
     * @param codigo Código numérico de la pregunta.
     * @return La pregunta encontrada o {@code null} si no existe.
     */
    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        for (Pregunta p : preguntas) {
            if (p.getId() == codigo) {
                return p;
            }
        }
        return null;
    }

    /**
     * Devuelve la lista completa de preguntas.
     *
     * @return Lista de objetos {@link Pregunta}.
     */
    @Override
    public List<Pregunta> listar() {
        return preguntas;
    }

    /**
     * Asigna una respuesta a una pregunta específica según su código.
     *
     * @param codigo    Código de la pregunta.
     * @param respuesta Texto con la respuesta dada por el usuario.
     */
    @Override
    public void responder(int codigo, String respuesta) {
        Pregunta p = buscarPorCodigo(codigo);
        if (p != null) {
            p.setRespuesta(respuesta);
        }
    }
}
