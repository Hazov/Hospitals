package ru.hazov.javahospitalsoap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hazov.javahospitalsoap.entity.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Disease findByName(String name);
}
