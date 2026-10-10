package com.jesus.mi_api_rest.repository;

import com.jesus.mi_api_rest.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

// Proporciona los métodos CRUD de Spring Data JPA (findAll, save, findById, deleteById)
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    // Contacto: La entidad a manejar
    // Long: El tipo de dato de la llave primaria (ID)
}