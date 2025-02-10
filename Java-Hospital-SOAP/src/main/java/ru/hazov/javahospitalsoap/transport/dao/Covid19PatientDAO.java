package ru.hazov.javahospitalsoap.transport.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hazov.javahospitalsoap.entity.Medicament;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Covid19PatientDAO {
    private String firstName;
    private String lastName;
    private String middleName;
    private Date birthDate;
    @Id
    private Long patientDiseaseId;
    private boolean diseaseFatalOutCome;
    private Date diseaseStartDate;
    private Date diseaseEndDate;
    @OneToMany
    private List<Medicament> medicaments = new ArrayList<>();
}
