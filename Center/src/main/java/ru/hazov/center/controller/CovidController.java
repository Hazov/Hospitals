package ru.hazov.center.controller;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hazov.center.dto.api.in.covidreport.Covid19PatientsReport;
import ru.hazov.center.dto.api.in.covidreport.CovidReportResponse;
import ru.hazov.center.dto.api.in.covidreport.SoapWrapper;
import ru.hazov.center.service.CovidService;

@RestController
@RequestMapping("/covid")

public class CovidController {
    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }
    @PostMapping("/covid19report")
    public @ResponseBody ResponseEntity<CovidReportResponse> covid19report(@RequestBody Covid19PatientsReport covid19PatientsReport) {
        try{
            covidService.saveReport(covid19PatientsReport);
            return new ResponseEntity<>(new CovidReportResponse(), HttpStatusCode.valueOf(200)) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500)) ;
        }

    }

    @PostMapping("/SOAPcovid19report")
    public @ResponseBody ResponseEntity<CovidReportResponse> covid19report(@RequestBody SoapWrapper<Covid19PatientsReport> wrapper) {
        Covid19PatientsReport covid19PatientsReport = wrapper.getChild();
        return covid19report(covid19PatientsReport);
    }
}
