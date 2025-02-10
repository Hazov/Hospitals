package ru.hazov.center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hazov.center.dto.api.in.covidreport.Covid19PatientsReport;
import ru.hazov.center.repository.Covid19ReportRepository;

@Service
public class CovidService {

    private final Covid19ReportRepository covid19ReportRepository;

    @Autowired
    public CovidService(Covid19ReportRepository covid19ReportRepository) {
        this.covid19ReportRepository = covid19ReportRepository;
    }

    public void saveReport(Covid19PatientsReport covid19PatientsReport) {
        covid19ReportRepository.save(covid19PatientsReport);
    }
}
