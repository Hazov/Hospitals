package ru.hazov.center.dto.covidreport;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.hazov.center.collections.Patient;

import java.util.List;

@Document
public record Covid19PatientsReport(@Id String id, List<Patient> covid19Patients) {

}

