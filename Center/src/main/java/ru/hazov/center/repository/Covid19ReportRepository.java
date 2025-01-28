package ru.hazov.center.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.hazov.center.dto.covidreport.Covid19PatientsReport;


public interface Covid19ReportRepository extends MongoRepository<Covid19PatientsReport, String> {

}
