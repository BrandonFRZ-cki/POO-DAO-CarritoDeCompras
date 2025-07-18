package ec.edu.ups.dao.impl.DAOArchivoBinario;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de ProductoDAO utilizando archivos binarios con RandomAccessFile.
 * Se escribe cada producto como: int (código), UTF (nombre), double (precio).
 */
public class ProductoDAOArchivoBinario implements ProductoDAO {

    private final String ruta;

    /**
     * Constructor que recibe la ruta del archivo binario.
     *
     * @param ruta Ruta del archivo donde se almacenarán los productos.
     */
    public ProductoDAOArchivoBinario(String ruta) {
        this.ruta = ruta;

        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear archivo binario", e);
            }
        }
    }

    /**
     * Crea (guarda) un nuevo producto al final del archivo binario.
     *
     * @param producto Producto a guardar.
     */
    @Override
    public void crear(Producto producto) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.seek(raf.length()); // Ir al final
            raf.writeInt(producto.getCodigo());
            raf.writeUTF(producto.getNombre());
            raf.writeDouble(producto.getPrecio());
        } catch (IOException e) {
            throw new RuntimeException("Error al escribir producto", e);
        }
    }

    /**
     * Busca un producto por código en el archivo binario.
     *
     * @param codigo Código del producto.
     * @return Producto encontrado o null.
     */
    @Override
    public Producto buscarPorCodigo(int codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int cod = raf.readInt();
                String nombre = raf.readUTF();
                double precio = raf.readDouble();
                if (cod == codigo) {
                    return new Producto(cod, nombre, precio);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer producto", e);
        }
        return null;
    }

    /**
     * Busca productos por nombre (que empiecen con el texto dado).
     *
     * @param nombre Texto inicial del nombre del producto.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : listarTodos()) {
            if (p.getNombre().toLowerCase().startsWith(nombre.toLowerCase())) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    /**
     * Actualiza un producto. Crea una lista temporal, la reescribe con cambios.
     *
     * @param producto Producto modificado.
     */
    @Override
    public void actualizar(Producto producto) {
        List<Producto> todos = listarTodos();
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).getCodigo() == producto.getCodigo()) {
                todos.set(i, producto);
                break;
            }
        }
        sobrescribirArchivo(todos);
    }

    /**
     * Elimina un producto por código.
     *
     * @param codigo Código del producto a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Producto> todos = listarTodos();
        todos.removeIf(p -> p.getCodigo() == codigo);
        sobrescribirArchivo(todos);
    }

    /**
     * Lista todos los productos almacenados en el archivo.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int codigo = raf.readInt();
                String nombre = raf.readUTF();
                double precio = raf.readDouble();
                productos.add(new Producto(codigo, nombre, precio));
            }
        } catch (EOFException eof) {
            // Fin de archivo normal
        } catch (IOException e) {
            throw new RuntimeException("Error al listar productos", e);
        }
        return productos;
    }

    /**
     * Método auxiliar que sobrescribe el archivo binario con una lista completa de productos.
     *
     * @param productos Lista de productos actualizada.
     */
    private void sobrescribirArchivo(List<Producto> productos) {
        try (RandomAccessFile raf = new RandomAccessFile(ruta, "rw")) {
            raf.setLength(0); // Borrar contenido
            for (Producto p : productos) {
                raf.writeInt(p.getCodigo());
                raf.writeUTF(p.getNombre());
                raf.writeDouble(p.getPrecio());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo", e);
        }
    }
}
