package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private List<Carrito> carritos;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private List<Pregunta> preguntasRespondidas;

    public Usuario() {

    }

    public Usuario(String nombreDeUsuario, String contrasenia, Rol rol,String nombre, String apellido, String email, String telefono) {
        this.username = nombreDeUsuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.carritos = new ArrayList<Carrito>();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.preguntasRespondidas = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Carrito> getCarritos() {
        return carritos;
    }

    public void agregarCarrito(Carrito carrito) {
        carritos.add(carrito);
    }

    public void setCarritos(List<Carrito> carritos) {
        this.carritos = carritos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public List<Pregunta> getPreguntasRespondidas() {
        return preguntasRespondidas;
    }
    public void agregarPregunta(Pregunta pregunta) {
        this.preguntasRespondidas.add(pregunta);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + username + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", rol=" + rol +
                '}';
    }
}
