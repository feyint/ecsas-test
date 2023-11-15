/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.test.api.repository;

import com.test.api.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author feyin
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
    
}
