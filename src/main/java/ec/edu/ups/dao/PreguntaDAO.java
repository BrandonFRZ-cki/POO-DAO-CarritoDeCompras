package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.List;

/**
 * Interfaz que define las operaciones básicas para gestionar preguntas de seguridad.
 * Es utilizada para abstraer el almacenamiento de las preguntas, permitiendo diferentes implementaciones (memoria, archivos, etc.).
 *
 * <p>Responsabilidades:
 * <ul>
 *     <li>Crear preguntas</li>
 *     <li>Responder preguntas</li>
 *     <li>Buscar preguntas por código</li>
 *     <li>Listar todas las preguntas</li>
 * </ul>
 * </p>
 *
 * @author Brandon
 * @version 1.0
 */
public interface PreguntaDAO {

    /**
     * Registra una nueva pregunta en el sistema.
     * @param pregunta Pregunta a guardar.
     */
    void crear(Pregunta pregunta);

    /**
     * Establece una respuesta para una pregunta identificada por su código.
     * @param codigo Código de la pregunta.
     * @param respuesta Texto de la respuesta.
     */
    void responder(int codigo, String respuesta);

    /**
     * Busca una pregunta en base a su código único.
     * @param codigo Código de la pregunta.
     * @return La pregunta encontrada o {@code null} si no existe.
     */
    Pregunta buscarPorCodigo(int codigo);

    /**
     * Retorna una lista con todas las preguntas almacenadas.
     * @return Lista de preguntas.
     */
    List<Pregunta> listar();
}
