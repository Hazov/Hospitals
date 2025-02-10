package ru.hazov.center.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hazov.center.dto.api.in.medicament_counts.MedicamentCount;
import ru.hazov.center.dto.api.out.medicament_count.EsbMedicamentCountsResponse;
import ru.hazov.center.dto.api.out.medicament_order.EsbMedicamentOrder;
import ru.hazov.center.dto.api.out.medicament_order.EsbMedicamentOrdersRequest;
import ru.hazov.center.exception.controller.medicament.NoAvailableMedicamentsException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicamentsService {

    private final RestService restService;


    @Autowired
    public MedicamentsService(RestService restService){
        this.restService = restService;
    }

    private Map<String, Integer> medicamentCountsFromHospitals(String medicamentName, int count) {
        EsbMedicamentCountsResponse response = restService.medicamentCountsEsbRequest(medicamentName);
        return response.getMedicamentCounts().stream().collect(
                Collectors.toMap(MedicamentCount::hospitalName, mc -> mc.response().getCount())
        );
    }

    public void orderMedicaments(String medicamentName, int count) {
        Map<String, Integer> countsMap = medicamentCountsFromHospitals(medicamentName, count);
        double factCount = getAvailableCount(count, countsMap);
        if(factCount == 0){
            throw new NoAvailableMedicamentsException();
        }

        List<EsbMedicamentOrder> medicamentOrders = countsMap.entrySet().stream()
                .map(entry -> new EsbMedicamentOrder(entry.getKey(), entry.getValue()))
                .toList();
        EsbMedicamentOrdersRequest esbMedicamentOrders = new EsbMedicamentOrdersRequest(medicamentOrders);
        EsbMedicamentCountsResponse response = restService.orderMedicamentEsbRequest(esbMedicamentOrders);
    }

    private int getAvailableCount(int count, Map<String, Integer> countsMap) {
        double availableCount = countsMap.values().stream().mapToDouble(c -> c).average().orElse(0);
        return (int) Math.round(Math.min(availableCount, count));
    }
}
