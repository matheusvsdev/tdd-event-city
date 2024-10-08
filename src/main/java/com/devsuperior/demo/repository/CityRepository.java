package com.devsuperior.demo.repository;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
