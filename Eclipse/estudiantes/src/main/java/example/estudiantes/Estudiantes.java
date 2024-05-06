package example.estudiantes;

import org.springframework.data.annotation.Id;

record Estudiantes(@Id int id, String nombre, String apellidos, String correo, String dni) {

}