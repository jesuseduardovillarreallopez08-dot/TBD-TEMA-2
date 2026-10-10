package com.jesus.mi_api_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// Marca la clase como una tabla en la base de datos
@Entity
// Lombok: genera automáticamente getters, setters, toString y equals
@Data
public class Contacto {

    // Indica que este campo es la llave primaria (Primary Key)
    @Id
    // Genera el ID autoincrementable automáticamente (1, 2, 3...)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Columnas de la tabla para el contacto
    private String nombre;
    private String telefono;
}