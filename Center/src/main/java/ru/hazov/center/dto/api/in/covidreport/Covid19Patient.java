package ru.hazov.center.dto.api.in.covidreport;

import java.util.Date;
import java.util.List;

public record Covid19Patient(
        String firstName,
        String lastName,
        String middleName,
        Date birthDate,
        boolean diseaseFatalOutCome,
        Date diseaseStartDate,
        Date diseaseEndDate,
        List<Medicament> medicaments) {
}
