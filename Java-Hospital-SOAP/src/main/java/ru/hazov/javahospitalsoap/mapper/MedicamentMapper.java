package ru.hazov.javahospitalsoap.mapper;

import org.springframework.stereotype.Component;
import ru.hazov.javahospitalsoap.entity.Medicament;
import ru.hazov.javahospitalsoap.jaxb_model.GetMedicamentCountResponse;

@Component
public class MedicamentMapper {
    public GetMedicamentCountResponse createGetMedicamentCountResponse(Medicament medicament) {
        GetMedicamentCountResponse response = new GetMedicamentCountResponse();
        response.setName(medicament.getName());
        response.setCount(medicament.getQuantity());
        return response;

    }
}
