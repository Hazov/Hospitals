package ru.hazov.center.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.hazov.center.dto.covidreport.Medicament;

import java.util.Date;
import java.util.List;

@Document
public record Patient(
        @Id String id,
        String firstName,
        String lastName,
        String middleName,
        Date birthDate,
        String diseaseName,
        Date diseaseStartDate,
        Date diseaseEndDate,
        boolean diseaseFatalOutcome,
        List<Medicament> medicaments
) {
}
