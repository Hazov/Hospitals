package ru.hazov.javahospitalsoap.service.scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import ru.hazov.javahospitalsoap.entity.Medicament;
import ru.hazov.javahospitalsoap.entity.PatientDisease;
import ru.hazov.javahospitalsoap.jaxb_model.Covid19PatientsReport;
import ru.hazov.javahospitalsoap.repository.PatientDiseaseRepository;
import ru.hazov.javahospitalsoap.transport.dao.Covid19PatientDAO;
import ru.hazov.javahospitalsoap.mapper.PatientMapper;
import ru.hazov.javahospitalsoap.repository.DiseaseRepository;
import ru.hazov.javahospitalsoap.repository.PatientRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableAsync
@Component
public class EsbReportService {
    private final DiseaseRepository diseasesRepository;
    private final PatientMapper patientMapper;
    private final PatientDiseaseRepository patientDiseaseRepository;
    @Value("${esb.address}")
    private String esbAddress;
    @Value("${covid19reportUrl}")
    private String covid19reportUrl;
    @Value("${covid19Name}")
    private String covid19Name;

    private final WebServiceTemplate webServiceTemplate;
    private final PatientRepository patientRepository;

    @Autowired
    EsbReportService(WebServiceTemplate webServiceTemplate, PatientRepository patientRepository, DiseaseRepository diseasesRepository, PatientMapper patientMapper, PatientDiseaseRepository patientDiseaseRepository) {
        this.webServiceTemplate = webServiceTemplate;
        this.patientRepository = patientRepository;
        this.diseasesRepository = diseasesRepository;
        this.patientMapper = patientMapper;
        this.patientDiseaseRepository = patientDiseaseRepository;
    }

    @Async
    @Scheduled(cron = "${esb.covid19.cron.send}")
    public void reportToEsb(){
        sendCovid19PatientsToESB();

    }
    public void sendCovid19PatientsToESB(){
        List<Covid19PatientDAO> covid19PatientDAOs = patientRepository.findCovid19PatientsForEsb(covid19Name);
        if(!covid19PatientDAOs.isEmpty()){
            List<Long> patientDiseaseIds = covid19PatientDAOs.stream().map(Covid19PatientDAO::getPatientDiseaseId).toList();
            List<PatientDisease> patientDiseases = patientDiseaseRepository.findPatientDiseasesByIdIn(patientDiseaseIds);
            Map<Long, List<Medicament>> medicamentsMap =
                    patientDiseases.stream().collect(Collectors.toMap(PatientDisease::getId, PatientDisease::getMedicaments));
            covid19PatientDAOs.forEach(covid19PatientDAO -> {
                List<Medicament> medicaments = medicamentsMap.get(covid19PatientDAO.getPatientDiseaseId());
                covid19PatientDAO.setMedicaments(medicaments);
            });


            Covid19PatientsReport covid19PatientsReport = patientMapper.createCovid19PatientsReport(covid19PatientDAOs);


            webServiceTemplate.marshalSendAndReceive(
                    String.format("%s", esbAddress),
                    covid19PatientsReport,
                    message -> ((SoapMessage)message).setSoapAction("covid19report")
            );
        }
    }
}



