package ru.hazov.javahospitalsoap.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;
import ru.hazov.javahospitalsoap.jaxb_model.CreateDiseaseRequest;
import ru.hazov.javahospitalsoap.jaxb_model.CreateDiseaseResponse;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsRequest;
import ru.hazov.javahospitalsoap.jaxb_model.GetPatientsResponse;
import ru.hazov.javahospitalsoap.service.DiseaseService;
import ru.hazov.javahospitalsoap.service.PatientService;

@Endpoint
public class DiseaseEndpoint {

    private final DiseaseService diseaseService;

    @Autowired
    DiseaseEndpoint(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @SoapAction("createDisease")
    @PayloadRoot(localPart = "createDisease.xsd", namespace = "http://www.hazov.ru/javahospitalsoap")
    @ResponsePayload
    public CreateDiseaseResponse getPatients(@RequestPayload CreateDiseaseRequest request) {
        CreateDiseaseResponse createDiseaseResponse = new CreateDiseaseResponse();
        try{
            diseaseService.createDisease(request);
            createDiseaseResponse.setResult(true);
            createDiseaseResponse.setReason("success");
        } catch (Exception e){
            createDiseaseResponse.setResult(false);
            createDiseaseResponse.setReason("Не удалось создать болезнь");
        }
        return createDiseaseResponse;
    }


}