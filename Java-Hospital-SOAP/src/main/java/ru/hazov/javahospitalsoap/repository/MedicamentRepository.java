package ru.hazov.javahospitalsoap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hazov.javahospitalsoap.entity.Medicament;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    Medicament findMedicamentByName(String medName);
}
