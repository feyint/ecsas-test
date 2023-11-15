/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.api.controller;

import com.test.api.entity.Person;
import com.test.api.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author eorozco
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/person")
public class ApiPersonController {
    @Autowired
    private PersonService personService;

    // Obtener todas las personas
    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    // Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personService.createPerson(person);
        return ResponseEntity.ok(savedPerson);
    }

    // Actualizar una persona existente
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return personService.updatePerson(id, person)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una persona por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
        return ResponseEntity.ok().<Void>build();
    }
}
