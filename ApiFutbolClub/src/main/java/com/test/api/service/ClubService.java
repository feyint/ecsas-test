/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.api.service;

import com.test.api.entity.Club;
import com.test.api.repository.ClubRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author feyin
 */
@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Optional<Club> getClubById(Integer id) {
        return clubRepository.findById(id);
    }

    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    public Optional<Club> updateClub(Integer id, Club updatedClub) {
        return clubRepository.findById(id)
                .map(existingClub -> {
                    existingClub.setName(updatedClub.getName());
                    // Actualizar otros campos según sea necesario
                    return clubRepository.save(existingClub);
                });
    }

    public void deleteClub(Integer id) {
        clubRepository.findById(id)
                .ifPresent(existingClub -> clubRepository.delete(existingClub));
    }
}
