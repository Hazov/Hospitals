package ru.hazov.javahospitalsoap.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;
import ru.hazov.javahospitalsoap.jaxb_model.CreatePatientRequest;
import ru.hazov.javahospitalsoap.jaxb_model.CreatePatientResponse;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsResponse;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsRequest;
import ru.hazov.javahospitalsoap.service.PatientService;

@Endpoint
public class PatientEndpoint {

    private final PatientService patientService;

    @Autowired
    PatientEndpoint(PatientService patientService) {
        this.patientService = patientService;
    }

    @SoapAction("getPatients")
    @PayloadRoot(localPart = "getPatientsRequest", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public GetPatientsResponse getPatients(@RequestPayload GetPatientsRequest patientRequest) {
        return patientService.getPatients(patientRequest);
    }

    @SoapAction("createPatient")
    @PayloadRoot(localPart = "createPatientRequest", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public CreatePatientResponse getPatients(@RequestPayload CreatePatientRequest request) {
        CreatePatientResponse createPatientResponse = new CreatePatientResponse();
        try{
            patientService.createPatient(request);
            createPatientResponse.setResult(true);
            createPatientResponse.setReason("success");
        } catch (Exception e){
            createPatientResponse.setResult(false);
            createPatientResponse.setReason("Не удалось создать пациента");
        }
        return createPatientResponse;

    }


}
