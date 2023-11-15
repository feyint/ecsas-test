/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.api.controller;

import com.test.api.entity.Club;
import com.test.api.service.ClubService;
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
@RequestMapping("/api/club")
public class ApiClubController {
     @Autowired
    private ClubService clubService;

    // Obtener todos los clubes
    @GetMapping
    public List<Club> getAllClubs() {
        return clubService.getAllClubs();
    }

    // Obtener un club por ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Integer id) {
        return clubService.getClubById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club savedClub = clubService.createClub(club);
        return ResponseEntity.ok(savedClub);
    }

    // Actualizar un club existente
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Integer id, @RequestBody Club club) {
        return clubService.updateClub(id, club)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un club por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Integer id) {
        clubService.deleteClub(id);
        return ResponseEntity.ok().<Void>build();
    }
}
