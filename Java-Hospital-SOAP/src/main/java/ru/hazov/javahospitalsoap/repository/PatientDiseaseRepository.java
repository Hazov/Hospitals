package ru.hazov.javahospitalsoap.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hazov.javahospitalsoap.entity.PatientDisease;

import java.util.List;

@Repository
public interface PatientDiseaseRepository extends JpaRepository<PatientDisease, Long> {
    @EntityGraph("withMedicaments")
    List<PatientDisease> findPatientDiseasesByIdIn(List<Long> ids);
}
