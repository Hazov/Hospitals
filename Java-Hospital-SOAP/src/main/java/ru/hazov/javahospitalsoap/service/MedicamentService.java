package ru.hazov.javahospitalsoap.service;

import org.springframework.stereotype.Service;
import ru.hazov.javahospitalsoap.entity.Medicament;
import ru.hazov.javahospitalsoap.repository.MedicamentRepository;

@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;

    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    public Medicament getByName(String name) {
        return medicamentRepository.findMedicamentByName(name);
    }
}
