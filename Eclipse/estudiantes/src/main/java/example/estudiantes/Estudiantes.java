package example.estudiantes;

import org.springframework.data.annotation.Id;

record Estudiantes(@Id Integer id, String nombre, String apellidos, String correo, String dni, String owner) {

}