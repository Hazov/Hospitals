package ru.hazov.javahospitalsoap.service;

import ru.hazov.javahospitalsoap.entity.PatientDisease;
import ru.hazov.javahospitalsoap.jaxb_model.CreatePatientRequest;
import ru.hazov.javahospitalsoap.jaxb_model.CreatePatientResponse;
import ru.hazov.javahospitalsoap.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hazov.javahospitalsoap.entity.Disease;
import ru.hazov.javahospitalsoap.entity.Patient;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsRequest;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsResponse;
import ru.hazov.javahospitalsoap.repository.PatientDiseaseRepository;
import ru.hazov.javahospitalsoap.repository.PatientRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DiseaseService diseaseService;
    private final PatientMapper patientMapper;
    private final PatientDiseaseRepository patientDiseaseRepository;


    @Autowired
    PatientService(PatientRepository patientRepository,
                   DiseaseService diseaseService,
                   PatientMapper patientMapper, PatientDiseaseRepository patientDiseaseRepository) {
        this.patientRepository = patientRepository;
        this.diseaseService = diseaseService;
        this.patientMapper = patientMapper;
        this.patientDiseaseRepository = patientDiseaseRepository;
    }

    public GetPatientsResponse getPatients(GetPatientsRequest patientRequest){
        Disease disease = diseaseService.getDiseaseByName(patientRequest.getDiseaseName());
        if(disease != null){
            List<Patient> patients = patientRepository.findPatientsByDiseasesDiseaseId(disease.getId());
            return patientMapper.createGetPatientsResponse(patients);
        }
        return new GetPatientsResponse();
    }

    public void createPatient(CreatePatientRequest request) {
        Disease disease = diseaseService.getDiseaseByName(request.getDiseaseName());
        Patient newPatient = new Patient();
        newPatient.setFirstName(request.getName());
        newPatient.setLastName(request.getLastName());
        newPatient.setMiddleName(request.getMiddleName());
        newPatient.setBirthDate(request.getBirthDate().toGregorianCalendar().getTime());
        patientRepository.save(newPatient);
        PatientDisease patientDisease = new PatientDisease();
        patientDisease.setPatient(newPatient);
        patientDisease.setDisease(disease);
        patientDisease.setStartDate(new Date());
        patientDiseaseRepository.save(patientDisease);
    }

}
