package ru.hazov.javahospitalsoap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.hazov.javahospitalsoap.entity.Patient;
import ru.hazov.javahospitalsoap.transport.dao.Covid19PatientDAO;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    //Пациенты по болезни, не отправленные в ESB, и закончившие болеть
    List<Patient> findPatientsByDiseasesDiseaseId(Long diseaseid);
    @Query(value = "SELECT new Covid19PatientDAO(p.firstName, p.lastName, p.middleName, p.birthDate, pd.id, pd.fatalOutcome, pd.startDate, pd.endDate, null) "
            + "FROM Patient p "
            + "JOIN p.diseases pd "
            + "JOIN pd.disease d "
            + "WHERE d.name like :diseaseName AND pd.sentToEsb IS FALSE")
    List<Covid19PatientDAO> findCovid19PatientsForEsb(String diseaseName);
}


