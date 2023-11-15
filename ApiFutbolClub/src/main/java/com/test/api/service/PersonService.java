/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.api.service;

import com.test.api.entity.Person;
import com.test.api.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author feyin
 */
@Service
public class PersonService {
   @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Optional<Person> updatePerson(Integer id, Person updatedPerson) {
        return personRepository.findById(id)
                .map(existingPerson -> {
                    existingPerson.setFirstName(updatedPerson.getFirstName());
                    existingPerson.setLastName(updatedPerson.getLastName());
                    existingPerson.setClubId(updatedPerson.getClubId());
                    existingPerson.setFieldPositionId(updatedPerson.getFieldPositionId());
                    existingPerson.setPersonTypeId(updatedPerson.getPersonTypeId());
                    // Actualizar otros campos según sea necesario
                    return personRepository.save(existingPerson);
                });
    }

    public void deletePerson(Integer id) {
        personRepository.findById(id)
                .ifPresent(existingPerson -> personRepository.delete(existingPerson));
    }
}
