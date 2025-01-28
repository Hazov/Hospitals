package ru.hazov.javahospitalsoap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hazov.javahospitalsoap.entity.Disease;
import ru.hazov.javahospitalsoap.jaxb_model.CreateDiseaseRequest;
import ru.hazov.javahospitalsoap.repository.DiseaseRepository;


@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public void createDisease(CreateDiseaseRequest request) {
        Disease disease = new Disease();
        disease.setName(request.getName());
        disease.setDescription(request.getDescription());
        diseaseRepository.save(disease);
    }

    public Disease getDiseaseByName(String diseaseName) {
        return diseaseRepository.findByName(diseaseName);
    }
}
