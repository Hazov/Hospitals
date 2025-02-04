package ru.hazov.center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hazov.center.dto.medicament_counts.MedicamentCount;
import ru.hazov.center.dto.medicament_counts.MedicamentCountsResponse;
import ru.hazov.center.kafka_events.medicament_order.MedicamentOrder;
import ru.hazov.center.kafka_events.medicament_order.MedicamentOrderEvent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MedicamentsService {

    private final RestService restService;


    @Autowired
    public MedicamentsService(RestService restService){
        this.restService = restService;
    }

    //10
// 9
// 4
// 5
// Максимум, 10 либо максимум
    private Map<String, Integer> medicamentCountsFromHospitals(String medicamentName, int count) {
        MedicamentCountsResponse response = restService.medicamentCountsEsbRequest(medicamentName);
        return response.medicamentCounts().stream().collect(
                Collectors.toMap(MedicamentCount::hospitalName, mc -> mc.response().count())
        );
    }

    public void orderMedicaments(String medicamentName, int count) {
        Map<String, Integer> countsMap = medicamentCountsFromHospitals(medicamentName, count);
        int min = countsMap.values().stream().min(Integer::compareTo).orElse(0);
        //TODO логика
        List<MedicamentOrder> medicamentOrders = countsMap.entrySet().stream()
                .map(entry -> new MedicamentOrder(entry.getKey(), false, entry.getValue()))
                .toList();
        MedicamentOrderEvent medicamentOrderEvent = new MedicamentOrderEvent(medicamentOrders);
        restService.toHospitals(medicamentOrderEvent);



    }
}
