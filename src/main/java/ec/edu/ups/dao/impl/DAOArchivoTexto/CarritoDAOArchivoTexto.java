package ec.edu.ups.dao.impl.DAOArchivoTexto;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Implementación de {@link CarritoDAO} que utiliza un archivo de texto plano como medio de persistencia.
 * Cada línea en el archivo representa un carrito, incluyendo sus productos como subestructura en formato específico.
 *
 * <p>Formato general de línea:</p>
 * <pre>
 * codigo,iva,fecha,total,username,[codigo,nombre,precio,cantidad:...]
 * </pre>
 *
 * @author Brandon
 * @version 1.0
 */
public class CarritoDAOArchivoTexto implements CarritoDAO {

    private final String ruta;

    /**
     * Constructor que recibe la ruta del archivo donde se guardarán los carritos.
     *
     * @param ruta Ruta absoluta o relativa del archivo.
     */
    public CarritoDAOArchivoTexto(String ruta) {
        this.ruta = ruta;

        File archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile(); // Crea el archivo si no existe
            } catch (IOException e) {
                throw new RuntimeException("No se pudo crear el archivo: " + ruta, e);
            }
        }
    }

    /**
     * Crea un nuevo carrito y lo guarda en el archivo si no está duplicado.
     *
     * @param carrito Carrito a guardar.
     */
    @Override
    public void crear(Carrito carrito) {
        if (buscarPorCodigo(carrito.getCodigo()) == null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
                bw.write(carrito.toString() + "\n");
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar carrito", e);
            }
        }
    }

    /**
     * Busca un carrito por su código único.
     *
     * @param codigo Código del carrito.
     * @return Carrito encontrado o {@code null} si no existe.
     */
    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listarTodos()) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    /**
     * Actualiza un carrito ya existente. Reescribe el archivo completo.
     *
     * @param carrito Carrito actualizado.
     */
    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == carrito.getCodigo()) {
                lista.set(i, carrito);
                break;
            }
        }
        sobrescribirArchivo(lista);
    }

    /**
     * Elimina un carrito por su código. Reescribe el archivo excluyendo el carrito eliminado.
     *
     * @param codigo Código del carrito a eliminar.
     */
    @Override
    public void eliminar(int codigo) {
        List<Carrito> lista = listarTodos();
        lista.removeIf(c -> c.getCodigo() == codigo);
        sobrescribirArchivo(lista);
    }

    /**
     * Lista todos los carritos almacenados en el archivo.
     *
     * @return Lista de objetos {@link Carrito}.
     */
    public List<Carrito> listarTodos() {
        List<Carrito> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Carrito carrito = fromArchivo(linea);
                if (carrito != null) {
                    lista.add(carrito);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer archivo de carritos", e);
        }
        Carrito.sincronizarContador(lista);

        return lista;
    }


    /**
     * Convierte una línea del archivo en un objeto {@link Carrito}, incluyendo sus ítems.
     *
     * @param linea Línea de texto del archivo.
     * @return Carrito convertido o {@code null} si hubo error.
     */
    private Carrito fromArchivo(String linea) {
        try {
            String[] partes = linea.split(",", 6);
            int codigo = Integer.parseInt(partes[0]);
            double ivaLeido = Double.parseDouble(partes[1]);
            String fechaStr = partes[2];
            double totalLeido = Double.parseDouble(partes[3]);
            String username = partes[4];
            String itemsStr = partes[5];

            // Crear carrito base
            Usuario usuario = new Usuario();
            usuario.setUsername(username);
            Carrito carrito = new Carrito(usuario);
            carrito.setCodigo(codigo);

            // Fecha
            GregorianCalendar fecha = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha.setTime(sdf.parse(fechaStr));
            carrito.setFechaCreacion(fecha);

            // Agregar items
            if (itemsStr.contains("[") && itemsStr.contains("]")) {
                String contenido = itemsStr.substring(itemsStr.indexOf("[") + 1, itemsStr.lastIndexOf("]"));
                String[] itemsSeparados = contenido.split(":");
                for (String itemLinea : itemsSeparados) {
                    if (itemLinea.isEmpty()) continue;
                    String[] datos = itemLinea.split(",");
                    int codigoProd = Integer.parseInt(datos[0]);
                    String nombreProd = datos[1];
                    double precioProd = Double.parseDouble(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);
                    Producto producto = new Producto(codigoProd, nombreProd, precioProd);
                    carrito.agregarProducto(producto, cantidad);
                }
            }

            return carrito;
        } catch (Exception e) {
            System.out.println("Error al parsear carrito: " + e.getMessage());
            return null;
        }
    }



    /**
     * Sobrescribe completamente el archivo con una lista actualizada de carritos.
     *
     * @param lista Lista actualizada de carritos.
     */
    private void sobrescribirArchivo(List<Carrito> lista) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, false))) {
            for (Carrito c : lista) {
                bw.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al sobrescribir archivo", e);
        }
    }
}
