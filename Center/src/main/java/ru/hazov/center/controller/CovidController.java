package ru.hazov.center.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.hazov.center.dto.covidreport.Covid19PatientsReport;
import ru.hazov.center.dto.covidreport.CovidResponse;
import ru.hazov.center.dto.covidreport.SoapWrapper;
import ru.hazov.center.service.CovidService;

@Controller
public class CovidController {
    private final CovidService covidService;

    public CovidController(CovidService covidService) {
        this.covidService = covidService;
    }

    @PostMapping("/covid19report")
    public @ResponseBody ResponseEntity<CovidResponse> covid19report(@RequestBody Covid19PatientsReport covid19PatientsReport) {
        try{
            covidService.saveReport(covid19PatientsReport);
            return new ResponseEntity<>(new CovidResponse(), HttpStatusCode.valueOf(200)) ;
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500)) ;
        }

    }

    @PostMapping("/SOAPcovid19report")
    public @ResponseBody ResponseEntity<CovidResponse> covid19report(@RequestBody SoapWrapper<Covid19PatientsReport> wrapper) {
        Covid19PatientsReport covid19PatientsReport = wrapper.getChild();
        return covid19report(covid19PatientsReport);
    }
}
