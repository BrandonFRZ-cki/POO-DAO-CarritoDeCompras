package ec.edu.ups.modelo;

import ec.edu.ups.util.exceptions.CedulaValidationException;
import ec.edu.ups.util.exceptions.PasswordException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
    private GregorianCalendar fechaNacimiento;
    private List<Pregunta> preguntasRespondidas;

    public Usuario() {
        this.carritos = new ArrayList<>();
        this.preguntasRespondidas = new ArrayList<>();
        this.fechaNacimiento = new GregorianCalendar();
    }

    public Usuario(String nombreDeUsuario, String contrasenia, Rol rol, String nombre, String apellido, String email, String telefono) {
        setUsername(nombreDeUsuario);
        this.carritos = new ArrayList<>();
        this.preguntasRespondidas = new ArrayList<>();
        this.fechaNacimiento = new GregorianCalendar();
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username != null && username.length() == 10) {
            try {
                for (int i = 0; i < username.length(); i++) {
                    Integer.parseInt(username.charAt(i) + "");
                }
            } catch (NumberFormatException e) {
                throw new CedulaValidationException("79"); // Solo números
            }

            int[] multiplicadores = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;

            for (int i = 0; i < 9; i++) {
                int digito = Integer.parseInt(username.charAt(i) + "");
                int producto = digito * multiplicadores[i];
                if (producto >= 10) producto -= 9;
                suma += producto;
            }

            int digitoVerificador = Integer.parseInt(username.charAt(9) + "");
            int resultado = (suma % 10 == 0) ? 0 : 10 - (suma % 10);

            if (resultado != digitoVerificador) {
                throw new CedulaValidationException("80"); // Verificador incorrecto
            }

            int provincia = Integer.parseInt(username.substring(0, 2));
            if (provincia < 0 || provincia > 24) {
                throw new CedulaValidationException("81"); // Código de provincia inválido
            }

            int tercerDigito = Integer.parseInt(username.charAt(2) + "");
            if (tercerDigito >= 6) {
                throw new CedulaValidationException("82"); // Tercer dígito inválido
            }

            this.username = username;
        } else {
            throw new CedulaValidationException("78"); // No tiene 10 dígitos
        }
    }


    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneEspecial = false;

        if (contrasenia != null && contrasenia.length() >= 6) {
            for (int i = 0; i < contrasenia.length(); i++) {
                char c = contrasenia.charAt(i);
                if (Character.isUpperCase(c)) tieneMayuscula = true;
                else if (Character.isLowerCase(c)) tieneMinuscula = true;
                else if (c == '@' || c == '_' || c == '-') tieneEspecial = true;
            }
            if (!tieneMayuscula) throw new PasswordException("84"); // Falta mayúscula
            if (!tieneMinuscula) throw new PasswordException("85"); // Falta minúscula
            if (!tieneEspecial)  throw new PasswordException("86"); // Falta carácter especial
            this.contrasenia = contrasenia;
        } else {
            throw new PasswordException("83"); // Longitud < 6
        }
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
        this.carritos.add(carrito);
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

    public GregorianCalendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Pregunta> getPreguntasRespondidas() {
        return preguntasRespondidas;
    }

    public void agregarPregunta(Pregunta pregunta) {
        this.preguntasRespondidas.add(pregunta);
    }

    public void setPreguntasRespondidas(List<Pregunta> preguntasRespondidas) {
        this.preguntasRespondidas = preguntasRespondidas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", rol=" + rol +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}