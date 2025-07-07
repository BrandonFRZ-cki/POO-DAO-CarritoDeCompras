# video
https://youtu.be/oGiakMkho_4
# Diagrama
https://drive.google.com/file/d/1-RRuPmbhxxhFoKN15FzddQU6sPR-AAdx/view?usp=sharing


# 🛒 Carrito de Compras — Proyecto Java Swing (POO | MVC | DAO | SOLID | MDI)

Este proyecto fue desarrollado como parte de la práctica número 2 en la asignatura de **Programación Orientada a Objetos**, durante el período académico 66, en la **Universidad Politécnica Salesiana**.

Su propósito principal es demostrar cómo aplicar patrones de diseño y principios SOLID en el desarrollo de una aplicación educativa con interfaz gráfica avanzada usando **Java Swing** y arquitectura basada en **MVC**, **DAO**, internacionalización y manejo de gráficos personalizados.

---

## 🎯 Objetivo del proyecto

Implementar un sistema funcional que simula el comportamiento de un **carrito de compras**, con funcionalidades completas de gestión de usuarios, productos y carritos. El enfoque incluye:

- Arquitectura desacoplada siguiendo el patrón **Modelo-Vista-Controlador (MVC)**.
- Acceso a datos con el patrón **DAO** e interfaces.
- Diseño de clases bajo los principios **SOLID**.
- Interfaz gráfica avanzada usando **MDI (Multiple Document Interface)** con `JDesktopPane`.
- Internacionalización con soporte dinámico para **Español**, **Inglés** y **Francés**.
- Recuperación de contraseña mediante preguntas de seguridad personalizadas.

---

## 🧠 Características principales

- Autenticación y registro de usuarios con almacenamiento de preguntas de seguridad.
- Recuperación segura de contraseña basada en respuestas del usuario.
- CRUD completo de **usuarios**, **productos** y **carritos de compras**.
- Cálculo automático de subtotal, IVA (12%) y total.
- Control de acceso según rol (`ADMINISTRADOR` y `USUARIO`).
- Interfaz visual personalizada con gráficos (`Graphics`) e íconos (`ImageIcon`).
- Internacionalización con archivos `.properties`, `ResourceBundle`, `NumberFormat`, y `DateFormat`.

---

## 🛠️ Tecnologías utilizadas

- 💻 Java 21
- 🧰 IntelliJ IDEA (recomendado con plugin Swing UI Designer)
- ☕ Java Swing para la GUI avanzada
- 📦 Estructura modular:
  - `modelo` – clases como `Usuario`, `Producto`, `Carrito`
  - `dao` – interfaces DAO y sus implementaciones en memoria
  - `controlador` – controladores MVC para cada módulo
  - `vista` – formularios y componentes gráficos
  - `util` – formateadores, manejadores de idioma, etc.
  - `resources` – archivos `.properties` por idioma + íconos visuales

---

## 🧱 Patrones y principios aplicados

| Técnica | Aplicación |
|--------|------------|
| **MVC** | Separación clara entre modelo, controlador y vista |
| **DAO** | Acceso a datos desacoplado con interfaces |
| **SOLID** | Se aplicaron SRP, OCP y DIP en el diseño de clases |
| **MDI** | Uso de `JDesktopPane` y `JInternalFrame` para ventanas múltiples |
| **I18N** | Uso de `ResourceBundle` y `.properties` para soporte multilingüe |

---

## 💡 Recomendaciones

- Ejecutar el proyecto desde `Main.java`.
- Cambiar entre idiomas desde el menú "Idioma" para comprobar la internacionalización.
- Probar las funcionalidades CRUD desde los menús principales.
- Explorar los gráficos personalizados en el área de escritorio (`Graphics`).
- Verificar los roles accediendo como `admin` y como `user`.

---

## 📂 Repositorio

🔗 [https://github.com/BrandonFRZ-cki/POO-DAO-CarritoDeCompras](https://github.com/BrandonFRZ-cki/POO-DAO-CarritoDeCompras)

Incluye:
- Evidencia de commits ordenados
- Archivos `.properties` por idioma
- Código fuente documentado y estructurado

---

## 🎓 Créditos

**Desarrollador:** Brandon Fernando Rivera Zambrano  
**Carrera:** Computación  
**Asignatura:** Programación Orientada a Objetos  
**Docente:** Gabriel León — GitHub: [gabuleon](https://github.com/gabuleon)

---

## 📃 Licencia

Este proyecto se desarrolló con fines educativos. Todos los derechos reservados para uso académico y demostrativo.