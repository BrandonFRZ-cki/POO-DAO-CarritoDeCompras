package ec.edu.ups.dao.impl.DAOArchivoBinario;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de PreguntaDAO que almacena preguntas en un archivo binario usando RandomAccessFile.
 * El formato binario por pregunta es:
 * [int: código de la pregunta] [UTF: respuesta (si existe, o string vacío)]
 */
public class PreguntaDAOArchivoBinario implements PreguntaDAO {

    private final String ruta;

    /**
     * Constructor que establece la ruta del archivo binario.
     * @param ruta Ruta del archivo donde se almacenarán las preguntas.
     */
    public PreguntaDAOArchivoBinario(String ruta) {
        this.ruta = ruta;

        // Si el archivo no existe, inicializa con 10 preguntas sin respuesta
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
                for (int i = 1; i <= 10; i++) {
                    raf.writeInt(i);         // Código
                    raf.writeUTF("");        // Respuesta vacía
                }
            } catch (IOException e) {
                System.err.println("Error al inicializar preguntas en archivo binario: " + e.getMessage());
            }
        }
    }
    /**
     * Crea o actualiza una pregunta en el archivo binario.
     * Si ya existe una pregunta con el mismo código, se actualiza la respuesta.
     * @param pregunta La pregunta a crear o actualizar.
     */
    @Override
    public void crear(Pregunta pregunta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                long posicionInicio = raf.getFilePointer();
                int cod = raf.readInt();
                raf.readUTF(); // Saltar respuesta existente

                if (cod == pregunta.getCodigo()) {
                    raf.seek(posicionInicio + 4); // Salta el int (4 bytes) y reescribe solo la respuesta
                    raf.writeUTF(pregunta.getRespuesta());
                    return;
                }
            }

            // Si no existía el código, lo agrega al final (opcional, no se usará en sistema fijo de 10 preguntas)
            raf.writeInt(pregunta.getCodigo());
            raf.writeUTF(pregunta.getRespuesta());

        } catch (IOException e) {
            System.err.println("Error al crear/actualizar pregunta en archivo binario: " + e.getMessage());
        }
    }

    /**
     * Busca una pregunta por su código.
     * @param codigo Código de la pregunta.
     * @return Objeto Pregunta encontrado o null si no existe.
     */
    @Override
    public Pregunta buscarPorCodigo(int codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int cod = raf.readInt();
                String respuesta = raf.readUTF();
                if (cod == codigo) {
                    Pregunta pregunta = new Pregunta(cod);
                    pregunta.setRespuesta(respuesta);
                    return pregunta;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al buscar pregunta en binario: " + e.getMessage());
        }
        return null;
    }

    /**
     * Lista todas las preguntas almacenadas.
     * @return Lista de objetos Pregunta.
     */
    @Override
    public List<Pregunta> listar() {
        List<Pregunta> preguntas = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int cod = raf.readInt();
                String respuesta = raf.readUTF();
                Pregunta pregunta = new Pregunta(cod);
                pregunta.setRespuesta(respuesta);
                preguntas.add(pregunta);
            }
        } catch (IOException e) {
            System.err.println("Error al listar preguntas desde archivo binario: " + e.getMessage());
        }
        return preguntas;
    }

    /**
     * Establece una respuesta a una pregunta ya existente.
     * @param codigo Código de la pregunta a responder.
     * @param respuesta Respuesta escrita por el usuario.
     */
    @Override
    public void responder(int codigo, String respuesta) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            while (raf.getFilePointer() < raf.length()) {
                long posicionInicio = raf.getFilePointer();
                int cod = raf.readInt();
                raf.readUTF(); // Leer la respuesta antigua (pero no usarla)

                if (cod == codigo) {
                    raf.seek(posicionInicio + 4); // Saltar código y sobreescribir solo la respuesta
                    raf.writeUTF(respuesta);
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al responder pregunta en binario: " + e.getMessage());
        }
    }
}
