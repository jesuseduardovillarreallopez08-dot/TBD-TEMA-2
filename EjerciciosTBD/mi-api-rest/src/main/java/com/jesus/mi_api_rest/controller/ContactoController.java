package com.jesus.mi_api_rest.controller;

import com.jesus.mi_api_rest.entity.Contacto;
import com.jesus.mi_api_rest.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
public class ContactoController {

    @Autowired
    private ContactoRepository contactoRepository;

    // 1. GET - Obtener todos los contactos
    @GetMapping
    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }

    // 2. GET - Obtener contacto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerPorId(@PathVariable Long id) {
        return contactoRepository.findById(id)
                .map(contacto -> ResponseEntity.ok(contacto))
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. POST - Crear un nuevo contacto
    @PostMapping
    public ResponseEntity<Contacto> crearContacto(@RequestBody Contacto contacto) {
        Contacto nuevoContacto = contactoRepository.save(contacto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoContacto);
    }

    // 4. PUT - Actualizar un contacto existente
    @PutMapping("/{id}")
    public ResponseEntity<Contacto> actualizarContacto(@PathVariable Long id, @RequestBody Contacto contactoDetalles) {
        return contactoRepository.findById(id)
                .map(contactoExistente -> {
                    contactoExistente.setNombre(contactoDetalles.getNombre());
                    contactoExistente.setTelefono(contactoDetalles.getTelefono());
                    Contacto actualizado = contactoRepository.save(contactoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE - Eliminar un contacto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id) {
        if (contactoRepository.existsById(id)) {
            contactoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}